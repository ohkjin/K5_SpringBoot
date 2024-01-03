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
//Rest pool controller (return value: view not data ) ∴MVC에서 V 생략
//Annotation가 담긴 class를 뒤져서 new instance 생성 후 container에 자동 저장
//(@Rest Controller, @Controller)
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
		}
	}
	
	@PutMapping("/member/{id}")
	public int updateMember(@PathVariable int id, @RequestBody MemberVO vo) {
		//혹은 멤버를 찾아와서 .set 을 하면 된다
		MemberVO orivo = getObject(id);
		if(orivo==null) return 0;
		if(vo.getName()!=null) orivo.setName(vo.getName());
		if(vo.getPass()!=null) orivo.setPass(vo.getPass());
//		list.set(idx,vo);		
		return 1;
	}

	@DeleteMapping("/member/{id}")
//	http://localhost:8000/memberbyId?id=2
	public int removeMember(@PathVariable int id) {
		MemberVO vo =getObject(id);
		try {
		list.remove(vo);
		}catch(Exception e) {
			e.getMessage();
		}
		return 1;
	}
	
	@PostMapping("/member")
	//@RequestBody: JSON이 body 안에 있음을 알려줌
	public int addMember(MemberVO vo) {
		if(vo.getId()==0) return 0;
		int id = vo.getId();
		if(getIndex(id)==0) {
			vo.setRegidate(new Date());
			list.add(vo);		
			return 1;
		}
		return 0;
	}
	
	@PostMapping("/member")
	//@RequestBody: JSON이 body 안에 있음을 알려줌
	public int addMemberJSON(@RequestBody MemberVO vo) {
		return addMember(vo);
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