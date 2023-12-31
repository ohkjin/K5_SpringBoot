package edu.pnu.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;


//@RestController
public class BoardController {
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}

	@GetMapping("/hello")
//	http://localhost:8080/hello?name=hi
	public String hello(String name) {
		return "Hello : "+name;
		
	}
	
//	@GetMapping("/getBoard")
////	http://localhost:8080/getBoard#
//	public BoardVO getBoard() {
//		// 객체를 생성시 json으로 리턴
//		BoardVO board = new BoardVO();
//		board.setSeq(1);
//		board.setTitle("test title");
//		board.setWriter("tester");
//		board.setContent("--------------");
//		board.setCreateDate(new Date());
//		board.setCnt(0);
//		return board;
//	}
//	
//	@GetMapping("/getBoardList")
//	public List<BoardVO> getBoardList() {
//		List<BoardVO> boardList = new ArrayList<>();
//		for(int i=0;i<10;i++) { //size set by oneself
//			BoardVO board = new BoardVO();
//			board.setSeq(i);
//			board.setTitle("test title"+ i);
//			board.setWriter("tester");
//			board.setContent("--------------");
//			board.setCreateDate(new Date());
//			board.setCnt(0);
//			
//			boardList.add(board);
//		}
//		return boardList;
//	}
//	
	
}
