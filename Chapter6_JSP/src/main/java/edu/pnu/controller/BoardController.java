package edu.pnu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.pnu.domain.Board;


@Controller
public class BoardController {
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = new ArrayList<>();
		for(long i = 1; i<=10;i++) {
			boardList.add(Board.builder()
					.seq(i)
					.title("게시판 프로그램 테스트")
					.writer("도우너")
					.content("ProgramTest")
					.cnt(0L).build());
		}
		// 이름 "boardList" 에 저장
		model.addAttribute("boardList",boardList);
		return "getBoardList";
	}
}
