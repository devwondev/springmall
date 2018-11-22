package com.example.springmall.sample.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class SampleFileDownloadController {
	@RequestMapping("/file/{fileName:.+}")
	// @PathVariable : URL 경로에 변수를 넣어줌
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
		System.out.println("SampleFileDownloadController.fileDownload()");
		// 상대경로 
		String path = request.getSession().getServletContext().getRealPath("/uploads");
		System.out.println(path+"<--path");
		// 파일의 File 객체 생성
		File file = new File(path+"/"+fileName);
		System.out.println(file+"<--file");
		/*파일이 존재할 때*/
		if (file.exists()) {
			System.out.println("file exist");
			/*
			 * getName() : 파일이나 폴더의 이름 넘겨준다.
			 * 객체의 URL에서 파일 이름의 확장자 부분을 기반으로 객체의 콘텐츠 타입을 추측
			*/
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				System.out.println(mimeType+"<--mimeType");
				mimeType = "application/octet-stream";
				/* MIME 타입
				 * text/plain은 텍스트 파일을 위한 기본값, 사람이 읽을 수 있어야 하며 이진 데이터를 포함하면 안됨
				 * application/octet-stream은 다른 모든 경우를 위한 기본값, 알려지지 않은 파일 타입은 이 타입을 사용해야함.
				 * plus) 브라우저들은 이런 파일들을 다룰 때, 사용자를 위험한 동작으로부터 보호하도록 개별적인 주의를 기울여야함
				*/
			}
			/*브라우저에 알려주는 정보*/
			response.setContentType(mimeType);
			// setHeader : "Content-Disposition"헤더의 값을 String.format("attachment; filename=\"" + file.getName() + "\"")으로 지정한다.
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());	// 용량, 원래는 int가 아니라 long
			/*InputStream / OutputStream 클래스는 둘 다 추상클래스여서 객체를 직접 만들지 못한다. 따라서 대부분 추상 메소드를 정의하여 모든 후손 클래스가 이를 강제로 구현하게 만드는 통일의 의미로 사용
			 * InputStream : 바이트 단위 입력 스트림의 최상위 클래스
			 * FileInputStream, FilterInputStream(BufferedInputStream, DataInputStream)
			 * OutputStream
			 * FileOutputStream, FilterOutputStream(BufferedOutputStream, DataOutputStream)
			 * */
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		
		/*파일이 존재하지 않을 때*/
		}else {
			System.out.println("file not exist");
		}
	}
}
