package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

/*--생성자 제외 가능 Annotation 1--*/ //auto로 찾아서 연결, 대신 초기화 세팅을 할 수 없음
@RequiredArgsConstructor 
@RestController
//@RequestMapping("/test") 호출시 /test/member
public class MemberController {
/*--생성자 제외 가능 Annotation 2--*/
//	@Autowired 
//	private MemberService ms;
	
	private final MemberService ms; //Annotation 1
	
//	private MemberService ms = new MemberService(); // 인스턴스 생성만
//	public MemberController() { // 생성자: 값 초기화 작업
//		ms = new MemberService();
//	}

	@PutMapping("/member")
	public int updateMember(@RequestBody MemberVO vo) {		
		return ms.updateMember(vo);
	}

	@DeleteMapping("/member")
//	http://localhost:8000/member?id=2
	public int removeMember(Integer id) {
		return ms.removeMember(id);
	}
	
	@PostMapping("/member")
	public int addMember(@RequestBody MemberVO vo) {
		return ms.addMember(vo);
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers(){
		return ms.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO by(@PathVariable Integer id) {
		return ms.by(id);		
	}
	
	
	

}