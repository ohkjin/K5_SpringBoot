package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bs;
	
	@GetMapping("/hello") // 이 이름 하의 html 파일을 찾는다
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello Thymeleaf^^");
	}
	
	@GetMapping("/hello2") // 이 이름 하의 html 파일을 찾는다
	public String hello2(Model model) {
		model.addAttribute("greeting", "Hello Thymeleaf^^");
		return "hello";
	}
	
	@GetMapping("/hello3") // 이 이름 하의 html 파일을 찾는다
	public ModelAndView hello3(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", "Hello Thymeleaf^^");
		mv.setViewName("hello");
		return mv;
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		model.addAttribute("boardList",bs.getBoardList());
		return "getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		model.addAttribute("board", bs.getBoard(board));
		return "getBoard";
	}
	
	@PostMapping("/insertBoard") // 입력이 끝나면 getBoardList로 redirect 
	public String insertBoard(Board board, Model model) {
		bs.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/insertBoard") // 실제로 데이터를 입력하는 곳
	public String insertBoardView() {
		return "insertBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		bs.updateBoard(board);
		return "redirect:/getBoardList";
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		bs.deleteBoard(board);
		return "forward:getBoardList";
	}
	
}
