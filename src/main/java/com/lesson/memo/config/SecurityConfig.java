package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // 誰でもアクセスできるページ（登録・ログイン画面、静的リソース）を設定
            	.requestMatchers("/admin/signup", "/admin/signin", "/css/**", "/js/**").permitAll()
                // それ以外のページはログインが必要
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                // ログイン画面のURLを指定
                .loginPage("/admin/signin")
                // ログイン処理を行うURLを指定
                .loginProcessingUrl("/admin/signin")
                // ログイン成功時の遷移先を指定
                .defaultSuccessUrl("/memo", true)
                .permitAll()
            )
            .logout(logout -> logout
            	.logoutUrl("/admin/logout")
                // ログアウト後の戻り先を指定
                .logoutSuccessUrl("/admin/signin")
                .permitAll()
            );
        return http.build();
    }
}