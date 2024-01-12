package edu.pnu;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class QueryAnnotationTest{

	@Autowired
	private BoardRepository repo;
	
	@Test
	public void testQueryAnnotationTest0() {
		List<Board> list = repo.queryAnnotationTest0("title1" , "true");
		
		System.out.println("==Result0==");
		for(Board b: list) {
			System.out.println("--->"+ b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest1() {
		List<Board> list = repo.queryAnnotationTest1("title1");
		
		System.out.println("==Result1==");
		for(Board b: list) {
			System.out.println("--->"+ b);
		}
	}
	
	@Test
	public void testQueryAnnotationTest2() {
		List<Object[]> list = repo.queryAnnotationTest2("title1");
		
		System.out.println("==Result2==");
		for(Object[] row: list) {
			System.out.println("--->"+ Arrays.toString(row));
		}
	}
	
	@Test
	public void testQueryAnnotationTest3() {
		List<Object[]> list = repo.queryAnnotationTest3("title1");
		
		System.out.println("==Result3==");
		list.forEach(o -> System.out.println(Arrays.toString(o)));
	}
	
	@Test
	public void testQueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0,  3, Sort.Direction.DESC, "seq");
		List<Board> list = repo.queryAnnotationTest4(paging);
		
		System.out.println("==Result4==");
		list.forEach(b -> System.out.println(b));
	}
}
