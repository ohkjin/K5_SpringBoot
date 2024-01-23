package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration 		// 이 클래스가 설정 클래스라고 정의 (IoC 컨테이너에 로드)
@EnableWebSecurity  // 스프링 시큐리티 적응에 필요한 객체들 자동생성
public class SecurityConfig {
	@Bean			// 이 매서드가 리턴하는 객체를 IoC컨테이너에 등록하라는 지시
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("MANAGER")
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                .anyRequest().permitAll());
        
        //csrf 보호 비활성화
        http.csrf(cf->cf.disable());
        
        //springBoot가 제공하는 Login
        //member나 admin을 들어가도 login이 뜸
        http.formLogin(form->form
        	.loginPage("/login")
        	.defaultSuccessUrl("/loginSuccess",true));
        
        //접근 권한이 없을시
        http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
        
        http.logout(logout->logout
        		.invalidateHttpSession(true) //현재 브라우저와 연결된 세션 강제종료
        		.deleteCookies("JSESSIONID") // 세션 아이디 쿠키 삭제
        		.logoutSuccessUrl("/login"));// 로그아웃 후 이동할 url
        
        return http.build();
    }
	
	//---암호화 추가--//
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
