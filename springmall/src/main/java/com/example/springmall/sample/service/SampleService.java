package com.example.springmall.sample.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springmall.sample.mapper.SampleMapper;
import com.example.springmall.sample.vo.Sample;

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
	// 1-1. 리스트
	public HashMap<String, Object> getSampleAll(int page){
		System.out.println("SampleService.getSampleAll()");
		HashMap<String, Integer> pageAction = new HashMap<String, Integer>();
		int rowPerPage = 10;
		int currentPage = 1;
		int lastPage=1;
		int lastNumber=10;
		int firstNumber=1;
		int selectSampleCount = sampleMapper.selectSampleCount();
		if(page != 1) {
			currentPage  = page;
		}
		lastPage = selectSampleCount/rowPerPage;
		if(selectSampleCount%rowPerPage!=0) {
			lastPage++;
		}
		if(currentPage>lastPage) {
			currentPage=lastPage;
		}else if(currentPage<1) {
			currentPage = 1;
		}
		lastNumber = ((currentPage+9)/10)*10;
		firstNumber = lastNumber-9;
		if((lastPage-1)/10 == (currentPage-1)/10) {
			lastNumber = lastPage;
        }
		int startRow = (currentPage-1)*rowPerPage;
		pageAction.put("startRow", startRow);
		pageAction.put("rowPerPage",rowPerPage);
		List<Sample> sampleList = sampleMapper.selectSampleAll(pageAction);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sampleList", sampleList);
		map.put("currentPage", currentPage);
		map.put("lastPage", lastPage);
		map.put("lastNumber", lastNumber);
		map.put("firstNumber", firstNumber);
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
		return sampleMapper.deleteSample(sampleNo);
	}
	// 3. 입력
	public int addSample(Sample sample) {
		System.out.println("SampleService.addSample()");
		return sampleMapper.insertSample(sample);
	}
	// 4-1. 수정 화면
	public Sample getSample(int sampleNo) {
		System.out.println("SampleService.getSample()");
		return sampleMapper.selectOne(sampleNo);
	}
	// 4-2. 수정 액션
	public int modifySample(Sample sample) {
		System.out.println("SampleService.modifySample()");
		return sampleMapper.updateSample(sample);
	}
}
