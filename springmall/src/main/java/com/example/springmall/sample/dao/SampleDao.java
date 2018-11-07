package com.example.springmall.sample.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.springmall.sample.vo.Sample;

public class SampleDao {
	private SqlSession sqlSession;
	// select
	public List<Sample> selectSampleList(){
		return sqlSession.selectList("com.example.springmall.sample.mapper.SampleMapper.selectSampleAll");
	}
	// delete
	public void deleteSample(int sampleNo) {
		sqlSession.delete("com.example.springmall.sample.mapper.SampleMapper.deleteSample", sampleNo);
	}
}
