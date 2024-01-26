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

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

		// 인증 객체
		private final AuthenticationManager authenticationManager;
		
		//1. Post/login 요청 시의 인증 메소드
		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
			
			// ObjectMapper: JSON read/write
			ObjectMapper mapper = new ObjectMapper();
			Member mem = null;
			try {
				mem = mapper.readValue(request.getInputStream(), Member.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			// 내부 member 토큰 생성 후 인증 진행
			// 성공 시 후처리 메소드에 auth(=authResult) 전가
			Authentication authToken = new UsernamePasswordAuthenticationToken(mem.getUsername(), mem.getPassword());
			// UserDetailsService를 상속받은 클래스의 loadUserByUsername 호출 (return type: UserDetails)
			Authentication auth = authenticationManager.authenticate(authToken);
			System.out.println("auth: "+auth);
			//auth: UsernamePasswordAuthenticationToken [Principal=org.springframework.security.core.userdetails.User [Username=member, Password=[PROTECTED], Enabled=true, AccountNonExpired=true, CredentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_MEMBER]], Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[ROLE_MEMBER]]
			
			return auth;
		}
		
		//2. 인증 성공 후 자동 호출되는 토큰 생성 후처리 메소드
		@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
			
			User user = (User)authResult.getPrincipal();
			String token = JWT.create()
							  .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*10))
							  .withClaim("username", user.getUsername())
							  .sign(Algorithm.HMAC256("testifChangeable"));
			
			response.addHeader("Authorization", "Bearer "+ token);
		}
}
