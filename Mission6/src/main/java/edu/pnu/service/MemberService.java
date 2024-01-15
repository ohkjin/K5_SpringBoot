package edu.pnu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDAOmysql;
import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.Member;
import edu.pnu.domain.MemberVO;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

// Instance 없이도 MemberDAO 자동 생성
@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepo;	

//	public int updateMember(Member vo) {
//		return memberRepo.updateMember(vo);
//	}
//
//	public int removeMember(int id) {
//		return memberRepo.removeMember(id);
//	}
//	
//	public int addMember(Member vo) {
//		return memberRepo.addMember(vo);
//	}
//	
//	public Page<Member> getMembers(){
//		return memberRepo.getMembers();
//	}
//	
//	public Member by(int id) {
//		return memberRepo.getMemberbyID(id);		
//	}


}
