package ua.opnu.practice1_template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests( auth -> auth
                        .anyRequest().permitAll()
                        .requestMatchers("/register", "/api/register", "/login").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole( "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USSER")
                        .anyRequest().permitAll()
                )
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl( "/success", true)
                        .permitAll()
                )
                .logout( logout -> logout.logoutUrl("/login?logout").permitAll());

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}