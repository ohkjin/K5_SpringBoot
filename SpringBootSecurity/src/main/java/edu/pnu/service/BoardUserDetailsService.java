package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memRepo;
	
	//UserDails라는 인터페이스 타입
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//memRepo 에서 사용자 정보검색
		Member member = memRepo.findById(username)
							   .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		System.out.println(member);
		
		//UserDetails 타입의 객체 생성 후 리턴 (o.s.s.core.userdetails.User)
		return new User(member.getUsername(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
