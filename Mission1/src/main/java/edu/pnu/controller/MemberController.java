package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
//@RequestMapping("/test") 호출시 /test/member
public class MemberController {
	private List<MemberVO> list;
	public MemberController() {
		System.out.println("success");
		list = new  ArrayList<>();
		for(int i = 1;i<=10;i++) {
			list.add(MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date()).build());
			
//			MemberVO vo = new MemberVO();
//			vo.setId(i);
//			vo.setName("name"+i);
//			vo.setPass("pass"+i);
//			vo.setRegidate((java.util.Date) new Date());
//			list.add(vo);
		}
	}

	@PutMapping("/member/{id}")
	public int updateMember(@PathVariable int id, @RequestBody MemberVO vo) {
		if(id==0) return 0;
		int idx = getIndex(id);
		list.set(idx,vo);		
		return 1;
	}

	@DeleteMapping("/member/{id}")
//	http://localhost:8000/memberbyId?id=2
	public int removeMember(@PathVariable int id) {
		if(id==0) return 0;
		MemberVO vo =getObject(id);
		list.remove(vo);
		return id;
	}
	
	@PostMapping("/member")
	public int addMember(@RequestBody MemberVO vo) {
		if(vo==null) return 0;
		int id = vo.getId();
		if(getIndex(id)==0) {
			list.add(vo);		
			return 1;
		}
		return 0;
	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers(){
		return list;
	}
	
	
	@GetMapping("/member/{id}")
//	http://localhost:8000/memberbyId?id=2
	public MemberVO by(@PathVariable int id) {
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