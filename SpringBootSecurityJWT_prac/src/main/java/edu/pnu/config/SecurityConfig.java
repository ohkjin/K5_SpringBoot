package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.handler.OAuth2SuccessHandler;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
		private final AuthenticationConfiguration authenticationConfiguration;
		private final MemberRepository memRepo;
		private final OAuth2SuccessHandler oAuth2SuccessHandler;
		
		
		
		
		@Bean
		SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
			//JS 에서 호출 가능하게 CSRF 보호 비활성화
			http.csrf(c->c.disable());
			
			http.authorizeHttpRequests(auth->auth
					.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
					.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")
					.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
					.anyRequest().permitAll());
			
			// Form을 이용한 로그인 금지
			http.formLogin(frmLogin->frmLogin.disable());
			// Http Basic 인증 방식 금지
			http.httpBasic(basic->basic.disable());
			//세션 유지 하지 않음 설정: Token
			http.sessionManagement(semt->semt.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
			
			//Add filter after Applied FilterChain of Spring Security
			http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
			http.addFilterBefore(new JWTAuthorizationFilter(memRepo), AuthorizationFilter.class);
			
			//Google Login -> DefaultOAuth2UserService
			// 추가적인 작업이 필요시에 상속된 클래스의 loadUser메소드에서 하면된다
			http.oauth2Login(oauth2->oauth2
//					.loginPage("/login")  // 생략시 OAuth제공 로그인 페이지가 뜬다
					.defaultSuccessUrl("/loginSuccess",true));
			
			//임의로 user를 생성 후 DB저장 및 Token Header에 생성
			http.oauth2Login(oauth2->oauth2
					.successHandler(oAuth2SuccessHandler));
			
			return http.build();
		}
}
