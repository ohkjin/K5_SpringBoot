package edu.pnu;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class DynamicQueryTest {
	@Autowired
	private DynamicBoardRepository boardRepo;
	

	private void testDynamicQuery(String searchCondition, String searchKeyword) {

		// 가변적인 파라미터 값에 따라 동적으로 and, or 에 해당하는 조건을 추가 가능
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		// 가변적 파라미터
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%"+ searchKeyword + "%"));
		}else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%"+searchKeyword+"%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("RESULT");
		boardList.forEach(b->System.out.println("--->"+b));
		
	}
	
	@Test
	@Order(1)
	public void testDyanmicQuery() {
		testDynamicQuery("TITLE", "title1");
		testDynamicQuery("CONTENT", "true");
	}
	private void testDynamicQuery2(Map<String,String> map) {
		// 가변적인 파라미터 값에 따라 동적으로 and, or 에 해당하는 조건을 추가 가능
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		// 가변적 파라미터
		if(map.get("TITLE")!=null) {
			builder.and(qboard.title.like("%"+ map.get("TITLE") + "%"));
		}
		if(map.get("CONTENT")!=null) {
			builder.and(qboard.content.like("%"+map.get("CONTENT")+"%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("RESULT");
		boardList.forEach(b->System.out.println("--->"+b));
		
	}
	@Test
	@Order(2)
	public void testDyanmicQuery2() {
		//Map을 이루는 조건문이 String이외일 경우 class를 생성 후 재조정
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "true");
		testDynamicQuery3(map);	
	}
	

	
	private void testDynamicQuery3(Map<String,String> map) {

		// 가변적인 파라미터 값에 따라 동적으로 and, or 에 해당하는 조건을 추가 가능
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		// 가변적 파라미터
		if(map.get("TITLE")!=null) {
			builder.and(qboard.title.like("%"+ map.get("TITLE") + "%"));
		}
		if(map.get("CONTENT")!=null) {
			builder.and(qboard.content.like("%"+map.get("CONTENT")+"%"));
		}
		
		Pageable paging = PageRequest.of(0, 5);
		Page<Board> boardList = boardRepo.findAll(builder,paging);
		
		System.out.println("RESULT");
		boardList.forEach(b->System.out.println("--->"+b));
		
	}
	
	@Test
	@Order(3)
	public void testDyanmicQuery3() {
		//Map을 이루는 조건문이 String이외일 경우 class를 생성 후 재조정
		//cnt
		Map<String, String> map = new HashMap<>();
		map.put("TITLE", "title1");
		map.put("CONTENT", "true");
		testDynamicQuery3(map);	
	}
	
	
}
