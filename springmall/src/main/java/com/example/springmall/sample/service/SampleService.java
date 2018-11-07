package com.example.springmall.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;

@Service
@Transactional
public class SampleService {
	// new 연산자 안쓸것
	@Autowired
	// 멤버변수 선언
	private SampleMapper sampleMapper;
	// 1. select
	public List<Sample> getSampleAll(){
		// 페이징 관련 코드
		// return sampleMapper.selectSampleAll();
		// 가상의 데이터 생성
		List<Sample> list= new ArrayList<Sample>();
		list.add(new Sample(1,"user1","1234"));
		list.add(new Sample(2,"user2","1234"));
		list.add(new Sample(3,"user3","1234"));
		return list;
	}
	// 2. delete
	public int removeSample(int sampleNo) {
		// return sampleMapper.deleteSample(sampleNo);
		return 1;
	}
}
