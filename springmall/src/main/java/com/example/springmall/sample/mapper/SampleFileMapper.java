package com.example.springmall.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.springmall.sample.vo.SampleFile;

@Mapper
public interface SampleFileMapper {
	int insertSampleFile(SampleFile sampleFile);
	SampleFile selectSampleFileOne(int sampleNo);
	int deleteSampleFile(int sampleNo);
	int updateSampleFile(SampleFile sampleFile);
}
