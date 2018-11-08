package com.example.springmall.sample.service;

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
	public List<Sample> getSampleAll(int startRow, int rowPerPage){
		System.out.println("SampleService.getSampleAll()");
		return sampleMapper.selectSampleAll(startRow, rowPerPage);
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
