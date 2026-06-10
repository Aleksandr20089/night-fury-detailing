package com.nightfury.detailing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Оставляем твое отключение CSRF, чтобы формы отправлялись без геморроя
                .csrf(csrf -> csrf.disable())

                // Настраиваем правила доступа
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Админка — только для ADMIN
                        .requestMatchers("/", "/services", "/book", "/booking-success", "/css/**", "/js/**").permitAll() // Главная, услуги и формы — для ВСЕХ
                        .anyRequest().authenticated() // Всё остальное требует авторизации
                )

                // Включаем стандартную форму логина
                .formLogin(form -> form
                        .defaultSuccessUrl("/admin/bookings", true) // После входа кидаем сразу в CRM
                        .permitAll()
                )

                // Настраиваем выход
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Прописываем одного админа в памяти
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("fury2026") // Пароль для входа в CRM
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}