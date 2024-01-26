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

@RequiredArgsConstructor
@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	private final MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepo.findById(username)
							   .orElseThrow(()->new UsernameNotFoundException("User Not Found!!"));
		if(member.getEnabled() == false) {
			// no Authorization in Header
			new UsernameNotFoundException("Deleted User");
			return null;
		}
		return new User(member.getUsername(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
