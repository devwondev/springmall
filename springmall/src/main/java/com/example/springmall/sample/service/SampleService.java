package com.example.springmall.sample.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.springmall.sample.mapper.SampleFileMapper;
import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;
import com.example.springmall.sample.vo.SampleAndSampleFile;
import com.example.springmall.sample.vo.SampleFile;
import com.example.springmall.sample.vo.SampleRequest;

@Service
/**
 * @Transactional : 쿼리문이 정상적으로 완료가 되고, 처리 도중 에러가 났을 때 쿼리를 자동 rollback 해주기 위해 사용
 *
 */
@Transactional

public class SampleService {
	/**
	 * @Autowired : 속성의 설정자 메서드에 해당하는 역할을 자동으로 수행
	*/
	@Autowired
	private SampleMapper sampleMapper;
	@Autowired
	private SampleFileMapper sampleFileMapper;
	// 1-1. 리스트
	public HashMap<String, Object> getSampleAll(int page){
		System.out.println("SampleService.getSampleAll()");
		HashMap<String, Integer> pageAction = new HashMap<String, Integer>();
		int pageBlock=5;	// 페이지 블록 처리할 변수 선언
		int rowPerPage = 10;	// 페이지 당 보여줄 글 목록
		int currentPage = 1;	// 현재 페이지
		int lastPage=1;	// 마지막 페이지
		int selectSampleCount = sampleMapper.selectSampleCount();	// 전체 글 갯수
		if(page != 1) {
			currentPage  = page;
		}
		lastPage = selectSampleCount/rowPerPage;	// 마지막 페이지는 전체 글 갯수에서 페이지 당 보여줄 글 목록으로 나눔
		if(selectSampleCount%rowPerPage!=0) {	// 나머지가 0이 아니라면, 전체 글이 101개 일때 총 페이지는 11페이지
			lastPage++;
		}
		if(currentPage>lastPage) {
			currentPage=lastPage;
		}else if(currentPage<1) {
			currentPage = 1;
		}
		// 페이징 블록 계산
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;	// 시작 페이지
		int endPage = startPage + pageBlock -1;	// 끝 페이지
		if(endPage>lastPage) {
			endPage = lastPage;
		}
		int startRow = (currentPage-1)*rowPerPage;
		pageAction.put("startRow", startRow);
		pageAction.put("rowPerPage",rowPerPage);
		List<SampleAndSampleFile> sampleList = sampleMapper.selectSampleAll(pageAction);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sampleList", sampleList);
		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}
	// 1-2. 전체 글 목록 갯수
	public int selectSampleCount() {
		System.out.println("SampleService.selectSampleCount()");
		return sampleMapper.selectSampleCount();
	}
	// 2. 삭제
	public int removeSample(int sampleNo) {
		System.out.println("SampleService.removeSample()");
		// sampleFile 정보 가져옴
		SampleFile sampleFile = sampleFileMapper.selectSampleFileOne(sampleNo);
		// 경로
		String path = sampleFile.getSampleFilePath();
		System.out.println(path+"<-path");
		// 파일이름
		String fileName = sampleFile.getSampleFileName();
		System.out.println(fileName+"<-fileName");
		// 확장자
		String ext = sampleFile.getSampleFileExt();
		System.out.println(ext+"<-ext");
		// 파일 삭제
		File f = new File(path+"\\"+fileName+"."+ext);
		f.delete();
		// 파일 삭제 먼저하고, 정보 삭제
		sampleFileMapper.deleteSampleFile(sampleNo);
		return sampleMapper.deleteSample(sampleNo);
	}
	// 3. 입력
	public int addSample(SampleRequest sampleRequest, HttpServletRequest request) {
		System.out.println("SampleService.addSample()");
		// 1
		Sample sample = new Sample();
		sample.setSampleId(sampleRequest.getSampleId());
		sample.setSamplePw(sampleRequest.getSamplePw());
		sampleMapper.insertSample(sample);
		// 2
		SampleFile sampleFile = new SampleFile();
		MultipartFile multipartFile = sampleRequest.getMultipartFile();
		// 1. SampleFileNo : AutoIncrement
		// 2. SampleNo
		sampleFile.setSampleNo(sample.getSampleNo());	// insertSample(sample) 후에 pk값이 sample 자리에 채워진다.
		// 3. SampleFilePath
		String path = request.getSession().getServletContext().getRealPath("/uploads");
		System.out.println(path+"<--path");
		File fileDirectory = new File(path);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("directory create!!!");
		}
		sampleFile.setSampleFilePath(path);
		// 4. 확장자
		String originalFileName = multipartFile.getOriginalFilename();	// 이름.확장자
		int point = originalFileName.lastIndexOf(".");
		String ext = originalFileName.substring(point+1);	// 끝에있는 점을 찾아서 그 다음부터의 문자를 찾아낸다.
		System.out.println(ext+"<--ext");
		sampleFile.setSampleFileExt(ext);
		// 5. 이름
		String fileName = UUID.randomUUID().toString();
		System.out.println(fileName+"<--fileName");
		sampleFile.setSampleFileName(fileName);
		// 6. 타입
		sampleFile.setSampleFileType(multipartFile.getContentType());
		System.out.println(multipartFile.getContentType()+"<--type");
		// 7. 크기
		sampleFile.setSampleFileSize(multipartFile.getSize());
		System.out.println(multipartFile.getSize()+"<--size");
		sampleFileMapper.insertSampleFile(sampleFile);
		// 내가 원하는 이름의 빈 파일 하나 만들기
		File f = new File(path+"\\"+fileName+"."+ext);
		// multipartfile의 파일을 위의 빈 파일로 복사
		try {
			multipartFile.transferTo(f);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// @Transactional에 의해 1->2
		return 0;
	}
	// 4-1. 수정 화면
	public HashMap<String, Object> getSample(int sampleNo) {
		System.out.println("SampleService.getSample()");
		HashMap<String, Object> map = new HashMap<String, Object>();
		SampleFile sampleFile = sampleFileMapper.selectSampleFileOne(sampleNo);
		Sample sample = sampleMapper.selectOne(sampleNo);
		map.put("sampleFile", sampleFile);
		map.put("sample", sample);
		return map;
	}
	// 4-2. 수정 액션
	public int modifySample(SampleRequest sampleRequest, String formFileName, HttpServletRequest request) {
		System.out.println("SampleService.modifySample()");
		// 1.
		Sample sample = new Sample();
		sample.setSampleNo(sampleRequest.getSampleNo());
		sample.setSampleId(sampleRequest.getSampleId());
		sample.setSamplePw(sampleRequest.getSamplePw());
		// 만약, 파일 수정을 안했다면
		if(sampleRequest.getMultipartFile().getOriginalFilename().length() != 0) {
			System.out.println("if문 실행");
			// 2.
			SampleFile sampleFile = new SampleFile();
			MultipartFile multipartFile = sampleRequest.getMultipartFile();
			sampleFile.setSampleNo(sample.getSampleNo());	// insertSample(sample) 후에 pk값이 sample 자리에 채워진다.
			// 3. 
			String path =  request.getSession().getServletContext().getRealPath("/uploads");
			System.out.println(path+"<--path");
			File fileDirectory = new File(path);
			if(!fileDirectory.exists()) {
				fileDirectory.mkdirs();
				System.out.println("directory create!!!");
			}
			sampleFile.setSampleFilePath(path);
			// 4.
			String originalFileName = multipartFile.getOriginalFilename();	// 이름.확장자
			int point = originalFileName.lastIndexOf(".");
			String ext = originalFileName.substring(point+1);	// 끝에있는 점을 찾아서 그 다음부터의 문자를 찾아낸다.
			System.out.println(ext+"<--ext");
			sampleFile.setSampleFileExt(ext);
			// 5.
			String fileName = UUID.randomUUID().toString();
			System.out.println(fileName+"<--fileName");
			sampleFile.setSampleFileName(fileName);
			// 6.
			sampleFile.setSampleFileType(multipartFile.getContentType());
			System.out.println(multipartFile.getContentType()+"<--type");
			// 7.
			sampleFile.setSampleFileSize(multipartFile.getSize());
			System.out.println(multipartFile.getSize()+"<--size");
			sampleMapper.updateSample(sample);
			sampleFileMapper.updateSampleFile(sampleFile);
			// 이전의 파일 삭제
			File beforeFile = new File(path+"\\"+formFileName);
			beforeFile.delete();
			// 새로운 파일 생성
			File f = new File(path+"\\"+fileName+"."+ext);
			try {
				multipartFile.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("else문 실행");
			sampleMapper.updateSample(sample);
		}
		
		return 0;
		
	}
	//	5. 로그인
	public Sample loginSample(Sample sample) {
		System.out.println("SampleService.loginSample()");
		return sampleMapper.loginSample(sample);
	}
}
