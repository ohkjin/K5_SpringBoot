package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
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
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final AuthenticationConfiguration authenticationConfiguration;
	private final MemberRepository memberRepo;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf->csrf.disable());
		
		// Role을 주는 방식에는 여러 메소드가 있다
		http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                .anyRequest().permitAll());
		
//		http.formLogin(frmLogin->frmLogin.disable()); // form을 이용한 로그인 사용하지 않음 (template 내의)
		http.httpBasic(basic->basic.disable());		  // Http Baisc 인증 방식 사용하지 않음
		
		// 세션을 유지하지 않겠다고 설정 ( Url호출 뒤 응답까지 유지되지만 응답 후 삭제 )
		http.sessionManagement(ssmn->
			ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터를 추가한다. (Spring Filter)
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		
		// 스프링 시큐리티가 등록한 필터들 중에서 AuthorizationFilter 앞에 앞에서 작성한 필터 삽입 
		//(Servlet Filter이기에 Spring Filter 앞뒤에 삽입)
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepo), AuthorizationFilter.class);
		
		return http.build();
	}
}
