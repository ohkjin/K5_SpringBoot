package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

//@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	@Order(1)
	public void testInsertBoard() {
		Board board = Board.builder()
				.title("title")
				.writer("tester")
				.content("content")
				.createDate(new Date())
				.cnt(0L).build();
		boardRepo.save(board);
		System.out.println(board); // DB에 올라간 객체 정보 확인
	}
	
//	@Test
	@Order(2)
	public void testUpdateBoard() {
		System.out.println("1st board");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("1st board EDIT");
		board.setTitle("Edited title");
		boardRepo.save(board);
	}
	
	@Test
	public void testDeleteBoard() {
		boardRepo.deleteById(1L);
	}

}
