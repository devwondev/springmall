package com.example.springmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MemberService {
	// component (Mapper, service(트랜잭션 처리), repository, controller)
	@Autowired
	private MemberMapper memberMapper;
	public int getCountMember() {
		return memberMapper.selectCountMember();
	}
}
