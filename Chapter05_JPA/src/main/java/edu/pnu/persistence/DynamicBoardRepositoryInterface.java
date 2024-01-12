package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Page;

import edu.pnu.domain.Board;

public interface DynamicBoardRepositoryInterface {

	public List<Board> testDynamicQuery1(String searchCondition, String searchKeyword);
	public void testDynamicQuery2(String searchCondition, String searchKeyword);
	public void testDynamicQuery3();
}
