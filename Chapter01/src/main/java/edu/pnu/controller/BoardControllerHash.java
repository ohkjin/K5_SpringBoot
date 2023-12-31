package edu.pnu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.BoardVO;


@RestController
public class BoardControllerHash {
	private Map<Integer,BoardVO> map;
	public BoardControllerHash() {
		System.out.println("===> BoardController2 생성");
		
		map = new HashMap<>();
		for (int i=1;i<=10;i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("test title"+ i);
			board.setWriter("tester");
			board.setContent("--------------");
			board.setCreateDate(new Date());
			board.setCnt(0);
			map.put(i,board);
		}
	}

	@GetMapping("/getBoardMap")
//	http://localhost:8080/getBoardMap?seq=2
	public BoardVO getBoardMap(Integer seq) {
		if(seq==null) return null;
		//loop방지를 위한 hashMap
		return map.get(seq);		
	}
	
	@GetMapping("/getBoardMap2/{seq}")
//	http://localhost:8000/getBoardMap2/2
	public BoardVO getBoard3(@PathVariable Integer seq) {
		if(seq==null) return null;
		return getBoardMap(seq);		
	}
	
	
}
