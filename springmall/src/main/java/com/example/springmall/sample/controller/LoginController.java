package com.example.springmall.sample.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.springmall.sample.service.SampleService;
import com.example.springmall.sample.vo.Sample;

@Controller
public class LoginController {
	@Autowired
	private SampleService sampleService;
	// index
	@RequestMapping(value="/sample/indexSample",method=RequestMethod.GET)
	public String indexSample(HttpSession session) {
		System.out.println("SampleController.indexSample()");
		String indexReturn = null;
		if(session.getAttribute("sessionSample")!=null) {
			indexReturn = "/sample/indexSample";
		}else {
			indexReturn = "/sample/indexSample";
		}
		return indexReturn;
	}
	
	// 로그인 폼
	@RequestMapping(value="/sample/loginSample",method=RequestMethod.GET)
	public String loginSample(HttpSession session) {
		System.out.println("LoginController.loginSample() GET");
		String loginReturn = null;
		if(session.getAttribute("sessionSample") != null) {
			loginReturn = "redirect:/sample/indexSample";
		}else {
			loginReturn = "sample/loginSample";
		}
		return loginReturn;
	}
	// 로그인 액션
	@RequestMapping(value="/sample/loginSample",method=RequestMethod.POST)
	public String loginSample(Sample sample, HttpSession session) {
		System.out.println("LoginController.loginSample() POST");
		String loginReturn = null;
		if(sampleService.loginSample(sample)!=null) {
			System.out.println("로그인성공");
			session.setAttribute("sessionSample", sampleService.loginSample(sample));
			loginReturn = "redirect:/sample/indexSample";
		}else {
			System.out.println("로그인실패");
			loginReturn = "/sample/loginSample";
		}
		return loginReturn;
	}
	// 로그아웃
	@RequestMapping(value="/sample/logoutSample",method=RequestMethod.GET)
	public String logoutSample(HttpSession session) {
		System.out.println("LoginController.logoutSample()");
		session.invalidate();
		return "redirect:/sample/indexSample";
	}
	
}
