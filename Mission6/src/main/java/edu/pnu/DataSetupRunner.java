package edu.pnu;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

//@Component //주석달시 실행X ddl-auto=create
public class DataSetupRunner implements ApplicationRunner {

@Autowired
private MemberRepository memberRepo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//  프로그램 실행 시 무조건 한번은 실행, 즉 초기화 메소드를 적어두면 무조건 실행시 초기화 
		System.out.println("Create+++++++++++++++++++");
		
		
		Member admin = Member.builder()
				.id("admin")
				.pass("asdf")
				.name("Admin")
				.role("Admin").build();
		memberRepo.save(admin);
		
		for(int i =1;i<=5;i++) {
			Member member = Member.builder()
					.id("member"+i)
					.pass("qwer")
					.name("MEMBER"+i)
					.role("User").build();
			memberRepo.save(member); //Cascade all operation
		}

	}

}
