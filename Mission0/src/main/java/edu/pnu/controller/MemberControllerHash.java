package edu.pnu.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;


@RestController
//@RequestMapping("/member/hash") 호출시 //test/member
public class MemberControllerHash {
	private Map<Integer,MemberVO> map;
	private List<MemberVO> list;
	public MemberControllerHash() {
		System.out.println("success");
		map = new  HashMap<>();
		for(int i = 1;i<=10;i++) {
			map.put(i, MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date()).build());

		}
		list = new ArrayList<MemberVO>(map.values());
	}

	@GetMapping("/memberhash")
	public List<MemberVO> getMap() {
		return list;
	}
	
	
	

}