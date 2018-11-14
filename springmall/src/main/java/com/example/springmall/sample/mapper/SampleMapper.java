package com.example.springmall.sample.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.Sample;
import com.example.springmall.sample.vo.SampleAndSampleFile;

@Mapper
public interface SampleMapper {
	// 1. select all(샘플 전체 목록)
	List<SampleAndSampleFile> selectSampleAll(HashMap<String, Integer> pageAction);
	// 1-1. select(전체 글 갯수)
	int selectSampleCount();
	// 2. delete(삭제)
	int deleteSample(int sampleNo);
	// 3. insert(입력)
	int insertSample(Sample sample);
	// 4-1. select(수정 화면)
	public abstract Sample selectOne(int sampleNo);
	// 4-2. update(수정 액션)
	int updateSample(Sample sample);
	// 5. select(로그인)
	Sample loginSample(Sample sample);
}
