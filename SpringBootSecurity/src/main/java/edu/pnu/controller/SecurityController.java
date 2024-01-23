package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	// Return이 String이면 return명을 호출
	@GetMapping({"/","/index"})
	public String index() {
		System.out.println("index Request");
		return "index";
	}
	
	// Return이 void면 url명을 호출
	@GetMapping({"/member"})
	public void member() {
		System.out.println("member Request");
	}
	
	@GetMapping({"/manager"})
	public void manager() {
		System.out.println("manager Request");
	}
	
	@GetMapping({"/admin"})
	public void admin() {
		System.out.println("admin Request");
	}
	
	
}
