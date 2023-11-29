package ksi.springbooks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/books_list").permitAll()
                        .requestMatchers("/new_book", "/edit_book/**", "/delete_book/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("categories_list", "/new_category", "/edit_category/**", "/delete_category/**").hasRole("ADMIN")
                        .requestMatchers("publishers_list", "/new_publisher", "/edit_publisher/**", "/delete_publisher/**").hasRole("ADMIN")
                        .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/books_list", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/books_list")
                );
        return http.build();
    }
        @Bean
    UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$12$1vNKd2FVGwSYi/Bu8nfS6OHApEgsEayDpbCotNjeEksipF0tQ3Vhm")
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user1")
                .password("$2a$12$1vNKd2FVGwSYi/Bu8nfS6OHApEgsEayDpbCotNjeEksipF0tQ3Vhm")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
