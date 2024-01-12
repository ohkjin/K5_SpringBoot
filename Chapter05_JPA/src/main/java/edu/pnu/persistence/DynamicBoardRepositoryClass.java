package edu.pnu.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;

@Repository
public class DynamicBoardRepositoryClass implements DynamicBoardRepositoryInterface{
	@Autowired
	private DynamicBoardRepository boardRepo;
	
	public List<Board> testDynamicQuery1(String searchCondition, String searchKeyword) {

		// 가변적인 파라미터 값에 따라 동적으로 and, or 에 해당하는 조건을 추가 가능
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		// 가변적 파라미터
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%"+ searchKeyword + "%"));
		}else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		
		//맞는 파라미터가 없을시 조건없이 진행
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList = boardRepo.findAll(builder,paging);
	
		return boardList.stream().toList();
		
	}


	@Override
	public void testDynamicQuery2(String searchCondition, String searchKeyword) {

		
	}

	@Override
	public void testDynamicQuery3() {
		// TODO Auto-generated method stub
		
	}
	
	
}
