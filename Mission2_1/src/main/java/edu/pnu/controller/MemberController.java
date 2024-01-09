package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.LobRetrievalFailureException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

//@RequiredArgsConstructor
@RequestMapping("/member")
@RestController //same as using @Controller and @ResponseBody(returnsJSON)
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService ms; // null로 초기화된 상태, 그 어디에도 값을 할당하는 method가 없음 
	// 방법1) 내가 직접 만들기: 생성자에 ms = new MemberService()가 추가
	// 방법2) springBoot에서 자동으로 생성하도록 MemberService에 Annotation 추가
		// Service에 @Service , null 초기화 위에 @Autowired: 사용시 이 이름을 가진 class에 연결해줌(interface시 그 interface를 구현한 단일(지정) class로 연결)
	
	public MemberController() {
		//Creates Instance by Scanning @Annotations
		log.debug("controller");
	}
	
	@GetMapping
	public List<MemberVO> getMembers(){
		return ms.getMembers();
	}
	@GetMapping("/{id}")
	public MemberVO getMemberbyID(@PathVariable Integer id) {
		return ms.getMemberbyID(id);
	}
	@PostMapping
	public int addMember(@RequestBody MemberVO vo) {
		return ms.addMember(vo);
	}
	@PostMapping("/params")
	public int addMemberParams(MemberVO vo) {
		return ms.addMember(vo);
	}
	@PutMapping
	public int updateMember(@RequestBody MemberVO vo) {
		return ms.updateMember(vo);
	}
	@DeleteMapping
	public int removeMember(Integer id) {
		return ms.removeMember(id);
	}

}
