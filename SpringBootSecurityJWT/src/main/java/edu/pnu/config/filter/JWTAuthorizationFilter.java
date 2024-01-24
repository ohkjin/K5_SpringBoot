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
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	//authorization설정을 위해 사용자의 Role정보를 읽어 들이는 객체 설정
	private final MemberRepository memberRepo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader("Authorization");		// 요청 헤더에서 인가을 얻어온다
		if (srcToken == null || !srcToken.startsWith("Bearer ")) {  // 없거나 "Bearer "로 시작하지 않는다면
			filterChain.doFilter(request, response);				// 필터를 그냥 통과	
			return;
		}
		String jwtToken = srcToken.replace("Bearer ", "");			// token만
		
		//토큰에서 username 추출
		String username = JWT.require(Algorithm.HMAC256("edu.pnu.jwt"))
							 .build().verify(jwtToken).getClaim("username").asString();
		
		Optional<Member> opt = memberRepo.findById(username);			// 토큰에서 얻은 username으로 DB를 검색하여 사용자를 검색	
		if(!opt.isPresent()) {										// 사용자가 존재하지 않을 시
			filterChain.doFilter(request, response);				// 필터를 그냥 통과
			return;
		}
		Member findmember = opt.get();
		
		// DB에서 읽은 사용자 정보를 이용하여 UserDtails 타입의 객체를 생성
		User user = new User(findmember.getUsername(), findmember.getPassword(),
				AuthorityUtils.createAuthorityList(findmember.getRole().toString()));
		
		// Authentication 객체를 생성: 사용자명과 권한 관리를 위한 정보를 입력( 암호는 불필요 )
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		// 시큐리티 세션에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		
		filterChain.doFilter(request, response);
	}
	// OncePerRequestFilter를 상속받게 되면 하나의 요청에 대해서 단한번의 필터를 거치게 된다
	// Forwarding되어 다른 페이지를 이동하게 되더라도 다시 이필터를 거치지 않게 한다
}
