package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;


@Repository //사용하고 싶을시 @Repository를 유일히 사용하면된다.
public class MemberDAOListImp implements MemberDAO {

	private List<MemberVO> list;
	MemberDAOListImp(){
		list = new ArrayList<>();
		for(int i=0;i<=5;i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date()).build());
		}
	}

	public int updateMember(MemberVO vo) {
		MemberVO orivo = getObject(vo.getId());
		if (orivo == null)
			return 0;
		if (vo.getName() != null)
			orivo.setName(vo.getName());
		if (vo.getPass() != null)
			orivo.setPass(vo.getPass());	
		return 1;
	}

	public int removeMember(Integer id) {
		MemberVO vo = getObject(id);
		try {
			list.remove(vo);
		} catch (Exception e) {
			e.getMessage();
		}
		return 1;
	}

	public int addMember(MemberVO vo) {
		if (vo.getId() == 0)
			return 0;
		int id = vo.getId();
		if (getIndex(id) == 0) {
			vo.setRegidate(new Date());
			list.add(vo);
			return 1;
		}
		return 0;
	}

	public int addMemberJSON(MemberVO vo) {
		return addMember(vo);
	}

	public List<MemberVO> getMembers() {
		return list;
	}

	public MemberVO getMemberbyID(Integer id) {
		if (id == 0)
			return null;
		return getObject(id);
	}

	public int getIndex(int id) {
		if (id == 0)
			return 0;
		int idx = 0;
		for (MemberVO s : list) {
			if (s.getId() == id)
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