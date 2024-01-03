package edu.pnu.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberDAO;
import edu.pnu.domain.MemberVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberDAO dao;
	private List<MemberVO> list;
	

	public int updateMember(int id,MemberVO vo) {
		//혹은 멤버를 찾아와서 .set 을 하면 된다
		MemberVO orivo = getObject(id);
		if(orivo==null) return 0;
		if(vo.getName()!=null) orivo.setName(vo.getName());
		if(vo.getPass()!=null) orivo.setPass(vo.getPass());
//		list.set(idx,vo);		
		return 1;
	}

	public int removeMember(int id) {
		if(id==0) return 0;
		MemberVO vo =getObject(id);
		list.remove(vo);
		return id;
	}
	
	public int addMember(MemberVO vo) {
		if(vo==null) return 0;
		int id = vo.getId();
		if(getIndex(id)==0) {
			list.add(vo);		
			return 1;
		}
		return 0;
	}
	
	public List<MemberVO> getMembers(){
		return dao.getMembers();
	}
	
	public MemberVO by(int id) {
		if(id==0) return null;
		return getObject(id);		
	}
	

	public int getIndex(int id) {
		if(id==0) return 0;
		int idx =0;
		for(MemberVO s:list) {
			if(s.getId()==id)
				return idx;
			idx++;
		}
		return 0;		
	}
	
	public MemberVO getObject(int id) {
		if(id==0) return null;
		for(MemberVO s:list) {
			if(s.getId()==id)
				return s;
		}
		return null;		
	}
	
	
	

}
