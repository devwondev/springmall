package com.example.springmall.sample.vo;

public class Sample {
	public int sampleNo;
	public String sampleId;
	public String samplePw;
	// private SampleFile sampleFile;	// 조인할때 필요함
	
	public Sample() {
		super();
		
	}
	public Sample(int sampleNo, String sampleId, String samplePw) {
		super();
		this.sampleNo = sampleNo;
		this.sampleId = sampleId;
		this.samplePw = samplePw;
	}
	
	public int getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(int sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getSampleId() {
		return sampleId;
	}
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	public String getSamplePw() {
		return samplePw;
	}
	
	public void setSamplePw(String samplePw) {
		this.samplePw = samplePw;
	}
	@Override
	public String toString() {
		return "Sample [sampleNo=" + sampleNo + ", sampleId=" + sampleId + ", samplePw=" + samplePw + "]";
	}
	
}
