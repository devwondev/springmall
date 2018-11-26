package com.example.springmall.sample.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.SampleAndSampleFile;
import com.example.springmall.sample.vo.SampleRequest;

@Controller
public class SampleController {
	/**
	 * @Autowired : 속성의 설정자 메서드에 해당하는 역할을 자동으로 수행
	*/
	@Autowired
	private SampleService sampleService;
	/**
	 * <pre>
	 * 1. 개요 : 샘플 전체 목록
	 * 2. 처리내용 : sampleNo, sampleId, samplePw 리스트를 보여준다.
	 * 3. 입력 Data : Model model, int currentPage
	 * 4. 리턴 Data : String /sample/sampleList
	 * </pre>
	 * @Method Name : sampleList
	 */
	@RequestMapping(value="/sample/sampleList", method=RequestMethod.GET)
	public String sampleList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		System.out.println("SampleController.sampleList()");
		HashMap<String, Object> map = sampleService.getSampleAll(page);
		List<SampleAndSampleFile> sampleList = (List<SampleAndSampleFile>) map.get("sampleList");
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("currentPage", map.get("currentPage"));
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		return "/sample/sampleList";	// view의 이름(forward)
	}
	/**
	 * <pre>
	 * 1. 개요 : 삭제
	 * 2. 처리내용 : 리스트에서 삭제버튼 누르면 sampleNo조건에 맞게 리스트에서 삭제 되도록 한다.
	 * 3. 입력 Data : int sampleNo
	 * 4. 리턴 Data : String redirect:/sample/sampleList
	 * </pre>
	 * @Method Name : removeSample
	 */
	@RequestMapping(value="/sample/removeSample", method=RequestMethod.GET)
	public String removeSample(@RequestParam(value="sampleNo") int sampleNo) {
		System.out.println("SampleController.removeSample()");
		if(sampleService.removeSample(sampleNo) == 1) {
			System.out.println(sampleNo+"번 데이터 삭제 성공");
		}else {
			System.out.println(sampleNo+"번 데이터 삭제 실패");
		}
		return "redirect:/sample/sampleList";	// redirect: -> view가 아님(redirect)
	}
	/**
	 * <pre>
	 * 1. 개요 : 입력 화면
	 * 2. 처리내용 : 입력 화면으로 forward한다.
	 * 3. 입력 Data : 없음
	 * 4. 리턴 Data : String sample/addSample
	 * </pre>
	 * @Method Name : addSample
	 */
	@RequestMapping(value="/sample/addSample", method=RequestMethod.GET)
	public String addSample() {
		System.out.println("SampleController.addSample() 입력화면");
		return "sample/addSample";
	}
	/**
	 * <pre>
	 * 1. 개요 : 입력 액션
	 * 2. 처리내용 : 입력화면에서 아이디와 비밀번호 입력하면 값이 입력되고, 리스트에 보여진다.
	 * 3. 입력 Data : Sample sample
 	 * 4. 리턴 Data : String redirect:/sample/sampleList
	 * </pre>
	 * @Method Name : addSample
	 */
	@RequestMapping(value="/sample/addSample", method=RequestMethod.POST)
	public String addSample(SampleRequest sampleRequest, HttpServletRequest request) {
		System.out.println("SampleController.addSample() 입력액션");
		System.out.println(sampleRequest.getMultipartFile()+"<--sampleRequest.getMultipartFile()");
		sampleService.addSample(sampleRequest, request);
		return "redirect:/sample/sampleList";
	}
	/**
	 * <pre>
	 * 1. 개요 : 상세 화면
	 * 2. 처리내용 : 리스트에서 상세버튼 누르면 sampleNo에 맞는 정보가 상세화면에 보여진다.
	 * 3. 입력 Data : Model model, int sampleNo
	 * 4. 리턴 Data : String sample/detailSample
	 * </pre>
	 * @Method Name : modifySample
	 */
	@RequestMapping(value="/sample/detailSample", method=RequestMethod.GET)
	public String modifySample(Model model, @RequestParam(value="sampleNo") int sampleNo) {
		System.out.println("SampleController.detailSample() 상세화면");
		HashMap<String, Object> map = sampleService.getSample(sampleNo);
		model.addAttribute("sampleFile", map.get("sampleFile"));
		model.addAttribute("sample", map.get("sample"));
		return "sample/detailSample";
	}
	/**
	 * <pre>
	 * 1. 개요 : 수정 처리
	 * 2. 처리내용 : 수정화면에서 값을 수정하면 수정되고, 리스트로 수정된 값이 보여진다.
	 * 3. 입력 Data : Sample sample
	 * 4. 리턴 Data : String redirect:/sample/sampleList
	 * </pre>
	 * @Method Name : modifySample
	 */
	@RequestMapping(value="/sample/detailSample", method=RequestMethod.POST)
	public String modifySample(SampleRequest sampleRequest, String formFileName, HttpServletRequest request) {
		System.out.println("SampleController.detailSample() 수정액션");
		sampleService.modifySample(sampleRequest, formFileName, request);
		return "redirect:/sample/sampleList";
	}

}
