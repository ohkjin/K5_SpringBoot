package edu.pnu;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;

@SpringBootTest
public class DataSetup {
	
	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
	public void setUpData() {
		Random rd = new Random();
		for(long i = 1;i<=10;i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
					.content("content"+i+rd.nextBoolean())
					.writer("writer"+rd.nextInt(5))
					.cnt(rd.nextLong(100)).build());
		}
	}
}
