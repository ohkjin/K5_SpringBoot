package edu.pnu.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memRepo;
	
	@Override
	// AuthenticationManager의 authenticate 메소드가 호출되면 실행
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member mem = memRepo.findById(username)
							.orElseThrow(()->new UsernameNotFoundException("User not found"));
		if(mem.getEnabled()==false) return null;
		
		return new User(mem.getUsername(), mem.getPassword(),
				AuthorityUtils.createAuthorityList(mem.getRole().toString()));
	}

}
