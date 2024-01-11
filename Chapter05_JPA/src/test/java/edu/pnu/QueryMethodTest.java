package edu.pnu;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
	@Order(0)
	public void testData() {
		Random rd = new Random();
		for(int i=1;i<=100;i++) {
			boardRepo.save(Board.builder()
								.title("title"+i)
								.writer("tester")
								.content("content"+rd.nextInt()+rd.nextBoolean())
								.createDate(new Date())
								.cnt(rd.nextLong(100)).build());
		}
	}
	@Test
	@Order(1)
	public void testFindByTitleLike() {
		Pageable paging = PageRequest.of(1, 5); //(페이지, 페이지 당 5 record)
		Page<Board> page = boardRepo.findByTitleLike("%1%",paging.next());
		System.out.println("총 페이지 수 : "+page.getTotalPages());
		System.out.println("현제 페이지 : "+page.getNumber());
		System.out.println("Sorting : "+page.getSort());
		List<Board> boardList = page.getContent();
		System.out.println("=".repeat(20)+"Result for findByTitleLike"+"=".repeat(20));
		for(Board b: boardList) {
			System.out.println(b.toString());
		}
		if(page.hasNext()) System.out.println("continue");
		
	}

//	@Test
	@Order(2)
	public void testFindByCnt() {
		List<Board> boardList = boardRepo.findByCntEquals(52L);
		
		System.out.println("\n"+"=".repeat(20)+"Result for findByCntEquals"+"=".repeat(20));
		for(Board b: boardList) {
			System.out.println(b.toString());
		}
	}
//	@Test
	@Order(3)
	public void testFindByTitleAndCnt() {
		List<Board> boardList = boardRepo.findByTitleLikeAndCntGreaterThan("%1%", 50L);
		
		System.out.println("\n"+"=".repeat(20)+"Result for findByTitleLikeCntGreaterThan"+"=".repeat(20));
		for(Board b: boardList) {
			System.out.println(b.toString());
		}
	}
	
//	@Test
	@Order(4)
	public void testFindByCntBtw() {
		List<Board> boardList = boardRepo.findByCntBetweenOrderBySeq(30L, 50L);
		
		System.out.println("\n"+"=".repeat(20)+"Result for findByCntBetweenOrderBySeq"+"=".repeat(20));
		for(Board b: boardList) {
			System.out.println(b.toString());
		}
	}
	
//	@Test
	@Order(5)
	public void TestFindByTitleLikeOrContentLikeOrderBySeqDesc() {
		List<Board> boardList = boardRepo.findByTitleLikeOrContentLikeOrderBySeqDesc("%1", "content10%"); //Endswith1, Startwith content10
		
		System.out.println("\n"+"=".repeat(20)+"Result for findByTitleLikeOrContentLikeOrderBySeqDesc"+"=".repeat(20));
		for(Board b: boardList) {
			System.out.println(b.toString());
		}
	}
	

}
