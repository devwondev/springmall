package com.example.springmall.sample.service;

import java.util.HashMap;
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
	public List<Sample> getSampleAll(HashMap<String, Object> map){
		int rowPerPage = 10;
		int startRow = ((int)map.get("currentPage")-1)*rowPerPage;
		int selectSampleCount = sampleMapper.selectSampleCount();
		int lastPage = selectSampleCount/rowPerPage;
		if(selectSampleCount%rowPerPage!=0) {
			lastPage++;
		}
		map.put("rowPerPage", rowPerPage);
		map.put("startRow", startRow);
		map.put("lastPage", lastPage);
		return sampleMapper.selectSampleAll(map);
	}
	// 2. delete
	public int removeSample(int sampleNo) {
		return sampleMapper.deleteSample(sampleNo);
	}
}
