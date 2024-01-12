package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/boardSearch")
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping
	public List<Board> testDynamicQuery1(String searchCondition, String searchKeyword) {
		return service.testDynamicQuery1(searchCondition, searchKeyword);
	}
	

}
