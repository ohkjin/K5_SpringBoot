package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.BoardRepository;
import edu.pnu.domain.Board;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository bRepo;
	
	public List<Board> getBoardList() {
		return bRepo.findAll();
	}

	public Board getBoard(Board board) {
		Board b = bRepo.findById(board.getSeq()).get();
		b.setCnt(b.getCnt()+1);
		bRepo.save(b);
		return b;
	}

	public void insertBoard(Board board) {
		bRepo.save(board);
	}
	
	//id가 존재시에는 update, 아닐시에는 post
	public Board updateBoard(Board board) {
		Board b = bRepo.findById(board.getSeq()).get();
		b= Board.builder()
		.seq(board.getSeq())
		.title(board.getTitle())
		.writer(board.getWriter())
		.content(board.getContent())
		.cnt(board.getCnt()).build();
		bRepo.save(b);
		return b;
	}
	
	public void deleteBoard(Board board) {
		bRepo.deleteById(board.getSeq());
	}
}
