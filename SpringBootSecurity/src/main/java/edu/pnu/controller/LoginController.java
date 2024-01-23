package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService ms;
	
	// Return이 void면 url명을 호출
	@GetMapping({"/login"})
	public void login() {}
	
	@GetMapping({"/loginSuccess"})
	public void loginSuccess() {}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {}
	
	// 로그인 세션 정보 확인용 URL
	@GetMapping({"/auth"})
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}
	
	//--회원가입--//
	// join.html:가입정보입력 View
	@GetMapping("/join")
	public void join() {}
	// welcome.html:가입환영 View
	@PostMapping("/join")
	public String joinProc(Member member) {
		ms.save(member);
		return "welcome";
	}
	
		
}
