package com.example.springmall.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.Sample;

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
	public String sampleList(Model model, @RequestParam(value="currentPage", defaultValue="1") int currentPage) {
		System.out.println("SampleController.sampleList()");
		int selectSampleCount = sampleService.selectSampleCount();
		int rowPerPage = 10;	// 페이지 당 보여줄 글 목록
		int startRow = (currentPage-1)*rowPerPage;
		int lastPage = selectSampleCount/rowPerPage;	// 마지막 페이지는 총 갯수를 페이지 당 보여줄 글 목록으로 나눈 몫
		// 만약, 전체 갯수가 100이면 10으로 나누었을 때  마지막 페이지는 10
		// 하지만, 전체 갯수가 101이리나면 10으로 나누었을 때  마지막 페이지는 11
		if(selectSampleCount % rowPerPage != 0) {	
			lastPage++;
		}
		List<Sample> sampleList = sampleService.getSampleAll(startRow, rowPerPage);
		model.addAttribute("sampleList", sampleList);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("currentPage", currentPage);
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
	public String addSample(Sample sample) {
		System.out.println("SampleController.addSample() 입력액션");
		if(sampleService.addSample(sample) == 1) {
			System.out.println(sample.getSampleId()+"의 데이터 입력 성공");
		}else {
			System.out.println(sample.getSampleId()+"의 데이터 입력 실패");
		}
		return "redirect:/sample/sampleList";
	}
	/**
	 * <pre>
	 * 1. 개요 : 수정 화면
	 * 2. 처리내용 : 리스트에서 수정버튼 누르면 sampleNo에 맞는 정보가 수정화면에 보여진다.
	 * 3. 입력 Data : Model model, int sampleNo
	 * 4. 리턴 Data : String sample/modifySample
	 * </pre>
	 * @Method Name : modifySample
	 */
	@RequestMapping(value="/sample/modifySample", method=RequestMethod.GET)
	public String modifySample(Model model, @RequestParam(value="sampleNo") int sampleNo) {
		System.out.println("SampleController.modifySample() 수정화면");
		Sample sample = sampleService.getSample(sampleNo);
		model.addAttribute("sample", sample);
		return "sample/modifySample";
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
	@RequestMapping(value="/sample/modifySample", method=RequestMethod.POST)
	public String modifySample(Sample sample) {
		System.out.println("SampleController.modifySample() 수정액션");
		if(sampleService.modifySample(sample) == 1) {
			System.out.println(sample.getSampleNo()+"의 데이터 수정 성공");
		}else {
			System.out.println(sample.getSampleNo()+"의 데이터 수정 실패");
		}
		return "redirect:/sample/sampleList";
	}
}
