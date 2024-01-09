package edu.pnu;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClient {
	private static void insertData(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board board = Board.builder()
					.title("JPA제목")
					.writer("Administrator")
					.content("JPA Content")
					.createDate(new Date())
					.cnt(0L).build();

			//글등록
			em.persist(board);
			
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
	}
	
	private static void searchData(EntityManagerFactory emf,Long seq) {
		EntityManager em = emf.createEntityManager();
		Board board = null;
		try {
			board = em.find(Board.class, seq);
			System.out.println("--->"+board.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void main(String[] args) {
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04_JPA");
		
		insertData(emf);
		searchData(emf,1L);
		
		// 만든 곳에서 닫아야함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ㅜㅜㅜㅜㅜㅜ
		emf.close();
	}
}
