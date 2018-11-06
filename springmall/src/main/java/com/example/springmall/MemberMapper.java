package com.example.springmall;

import org.apache.ibatis.annotations.Mapper;
// MemberMapper의 자식객체 이미 만들어져있음
// spring -> spring contain이라부름
// spring contain의 객체 -> 스프링 빈
@Mapper
public interface MemberMapper {
	
	int selectCountMember();
}
