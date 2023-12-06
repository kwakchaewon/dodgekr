package dodgekr.lolcommunity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
                // 1. 모든 인증되지 않은 요청을 허락
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())

                // 2. formLogin 메서드: 로그인 설정 담당.
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login") // 로그인 폼
                        .defaultSuccessUrl("/")) // 로그인 성공 후 URL

                // 3. logout 메서드: 로그아웃 설정
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))  // 로그아웃 URL
                        .logoutSuccessUrl("/") // 로그아웃 후 URL
                        .invalidateHttpSession(true)) // 세션 초기화
        ;
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
