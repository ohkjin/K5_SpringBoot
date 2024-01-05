package edu.pnu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.dao.MemberDAOH2Impl;
import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

// Instance 없이도 MemberDAO 자동 생성
@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberDAO dao;	

	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	public int removeMember(int id) {
		return dao.removeMember(id);
	}
	
	public int addMember(MemberVO vo) {
		return dao.addMember(vo);
	}
	
	public List<MemberVO> getMembers(){
		return dao.getMembers();
	}
	
	public MemberVO by(int id) {
		return dao.getMemberbyID(id);		
	}


}
