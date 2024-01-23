package edu.pnu.service;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memRepo;
	
	public void save(Member member) {
		memRepo.save(member);
	}
}
