package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
//	public MemberService() {
//		System.out.println("service");
//	}
//	
	public List<MemberVO> getMembers(){
		return dao.getMembers();
	}
	
	public MemberVO getMemberbyID(Integer id) {
		return dao.getMemberbyID(id);
	}

	public int updateMember(MemberVO vo) {
		return dao.updateMember(vo);
	}

	public int removeMember(Integer id) {
		return dao.removeMember(id);
	}

	public int addMember(MemberVO vo) {
		return dao.addMember(vo);
	}


}
