package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

//@SpringBootTest
public class MemberInitialize {
	
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Test
	public void saveMembers() {
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("qwer"))
				.role(Role.ROLE_MEMBER)
				.enabled(true).build());
		
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("qwer"))
				.role(Role.ROLE_MANAGER)
				.enabled(true).build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("qwer"))
				.role(Role.ROLE_ADMIN)
				.enabled(true).build());
		
	}
}
