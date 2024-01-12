package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.DynamicBoardRepositoryInterface;

@Service
public class BoardService {
	@Autowired
	DynamicBoardRepositoryInterface repo;
	
	public List<Board> testDynamicQuery1(String searchCondition, String searchKeyword) {
		return repo.testDynamicQuery1(searchCondition, searchKeyword);
		
	}
}
