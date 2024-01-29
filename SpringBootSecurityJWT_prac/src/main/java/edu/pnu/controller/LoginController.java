package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
//	@GetMapping("/login")
//	public String login() {
//		return "index";
//	}
	
	//Login Session 정보 확인용 URL
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) return "OAuth2:null";
		
		// 자동 회원가입시
		System.out.println("attributes:" + user.getAttributes());
		return "OAuth2:"+user;
	}
	
}
