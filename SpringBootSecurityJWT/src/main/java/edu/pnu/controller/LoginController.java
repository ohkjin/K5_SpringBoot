package edu.pnu.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import lombok.RequiredArgsConstructor;
//import edu.pnu.persistence.MemberRepository;

@Controller // Back 제공 뷰 (XRestController)
@RequiredArgsConstructor
public class LoginController {

//	private final MemberRepository memberRepo;
	
	// Return이 void면 url명을 호출
	@GetMapping({"/login"})
	public String login() {
		// LoginFrm disabled
		// View를 사용하려면 ThymeLeaf
		return "login";
	}
	
	@GetMapping({"/loginSuccess"})
	public @ResponseBody String loginSuccess() {
		return "success";
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {}
	
	// 로그인 세션 정보 확인용 URL
	@GetMapping({"/auth"})
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}
	
//	//--회원가입--//
//	// join.html:가입정보입력 View
//	@GetMapping("/join")
//	public void join() {}
//	// welcome.html:가입환영 View
//	@PostMapping("/join")
//	public String joinProc(Member member) {
//		memberRepo.save(member);
//		return "welcome";
//	}
	
}
