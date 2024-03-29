package edu.pnu.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// 1. POST/ login 요청이 들어오면 이 필터가 실행
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	//인증 객체
	private final AuthenticationManager authenticationManager;
	
	// 1.1 Post/login 요청이 왔을 떄 인증 시도 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// request에서 json 타입의 [username/password]를 읽어서 Member 객체 생성
		ObjectMapper mapper = new ObjectMapper();
		Member member = null;
		try {
			member = mapper.readValue(request.getInputStream(), Member.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Security에게 로그인 요청에 필요한 객체 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
		
		// 인증 진행 -> UserDetailsService를 상속받은 클래스(SecurityUserDetailsService.java)의 loadUserByUsername 호출
		Authentication auth = authenticationManager.authenticate(authToken);
		System.out.println("auth: "+auth);
		
		return auth;
	}
	
	// 1.2 위에서 인증 성공했을 때 자동으로 호출되는 후처리 메소드
	// 인증, 인가 토큰 생성
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		// 인증 결과 생성된 Authentication 객체에서 필요한 정보를 읽어서 토큰을 만들어서 헤더 추가
		User user = (User)authResult.getPrincipal();
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10)) //10 min
				.withClaim("username", user.getUsername())
				.sign(Algorithm.HMAC256("edu.pnu.jwt"));
		// 답변
		response.addHeader("Authorization","Bearer "+ token); //Bearer 뒤에 " " 필수
	}
	// Chain.doFilter 가 없어서 다음 필터에 넘기지 않고 다시 return
}
