package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;


@RestController
public class BoardController2 {
	private List<BoardVO> list;
	public BoardController2() {
		System.out.println("===> BoardController2 생성");
		
		list = new ArrayList<>();
		for (int i=1;i<=10;i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("test title"+ i);
			board.setWriter("tester");
			board.setContent("--------------");
			board.setCreateDate(new Date());
			board.setCnt(0);
			
			list.add(board);
		}
	}

	@GetMapping("/getBoardSeq")
//	http://localhost:8080/getBoardSeq?seq=2
	public BoardVO getBoardSeq(Integer seq) {
		if(seq==null) return null;
		//생성자에서 list생성: seq를 가져온다.
		for(BoardVO s:list)
			if(s.getSeq()==seq)
				return s;
		//loop방지를 위한 hashMap
		return null;		
	}
	
	@GetMapping("/getBoardSeq2/{seq}")
//	http://localhost:8000/getBoard3/2
	public BoardVO getBoard3(@PathVariable Integer seq) {
		if(seq==null) return null;
		return getBoardSeq(seq);		
	}
	
	
}
