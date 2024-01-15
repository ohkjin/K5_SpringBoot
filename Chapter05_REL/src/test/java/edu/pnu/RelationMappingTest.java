package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
//	@Test
	public void testManytoOneInsert() {
		Member member1 = Member.builder()
				.id("mem1")
				.pass("1111")
				.name("member1")
				.role("User").build();
//		memberRepo.save(member1);
		
		Member member2 = Member.builder()
				.id("mem2")
				.pass("2222")
				.name("Admin1")
				.role("Admin").build();
//		memberRepo.save(member2);
		
		for(int i =1;i<=3;i++) {
			Board board = Board.builder()
					.member(member1)
					.title("page by member1: "+i)
					.content("content by member1: "+i)
					.createDate(new Date())
					.cnt(0L).build();
//			boardRepo.save(board);
		}
		for(int i =1;i<=3;i++) {
			Board board = Board.builder()
					.member(member2)
					.title("page by Admin1: "+i)
					.content("content by Admin1: "+i)
					.createDate(new Date())
					.cnt(0L).build();
//			boardRepo.save(board);
		}
	}
	
	@Test
	public void testManyToOneSelect() {
		Pageable paging = PageRequest.of(1, 5); //(페이지, 페이지 당 5 record)
		Page<Board> page = boardRepo.findByTitleLike("%1%",paging);
//		Board board = boardRepo.findById(5L).get();
		System.out.println(page);
	}
	
//	@Test
	@Transactional
	public void testManyToOneSelectContent() {
		Board board = boardRepo.findById(5L).get();
		System.out.println(board.getTitle());
		System.out.println(board.getMember().getName());
	}
	
//	@Test
	public void testTwoWayMapping() { //상호호출 양방향 참조로 인한 순환 참조 따라서 호출고리를 끊어야함
		Member member = memberRepo.findById("mem1").get();
		
		System.out.println("=".repeat(30));
		System.out.println(member.getName()+"가(이) 저장한 게시글 목록");
		System.out.println("=".repeat(30));
		List<Board> list = member.getBoardList();
		for(Board board: list) {
			System.out.println(board.toString());
		}
	}
}
