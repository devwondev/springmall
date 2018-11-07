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
	@Autowired
	private SampleService sampleService;
	// 1. 샘플목록
	@RequestMapping(value="/sample/sampleList", method=RequestMethod.GET)
	public String sampleList(Model model) {	// Model model = new Model();
		List<Sample> sampleList = sampleService.getSampleAll();
		model.addAttribute("sampleList", sampleList);
		return "/sample/sampleList";	// view의 이름(forward)
		/*
		 * ModelAndView mav = new ModelAndView();
		 * mav.setModel()
		 * mav.setViewName();
		 * return mav;
		 * */
	}
	// 2. 삭제
	@RequestMapping(value="/sample/removeSample", method=RequestMethod.GET)
	public String removeSample(@RequestParam(value="sampleNo") int sampleNo) {
		if(sampleService.removeSample(sampleNo)==1) {
			System.out.println(sampleNo+"번 데이터 삭제 성공");
		}
		return "redirect:/sample/sampleList";	// redirect: -> view가 아님(redirect)
	}
}
