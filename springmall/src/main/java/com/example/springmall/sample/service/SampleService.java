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
		List<Sample> sampleList = sampleMapper.selectSampleAll(pageAction);
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
