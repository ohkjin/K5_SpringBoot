package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	// Role 정보를 읽기위한 객체
	private final MemberRepository memRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader("Authorization");
		// 띄어쓰기 주의!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(srcToken == null||!srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// 띄어쓰기 주의!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		String jwtToken = srcToken.replace("Bearer ", "");
		
		String username = JWT.require(Algorithm.HMAC256("testifChangeable"))
				.build().verify(jwtToken).getClaim("username").asString();
		Optional<Member> opt = memRepo.findById(username);
		if(!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		Member findmem = opt.get();
		
		// DB에서 사용자 정보를 읽어서 UserDetails 타입 객체 생성
		User user = new User(findmem.getUsername(), findmem.getPassword(),
				AuthorityUtils.createAuthorityList(findmem.getRole().toString()));
		
		// Authentication 객체 생성: (username, password, authority)
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// Security 세션에 auth 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
}
