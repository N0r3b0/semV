package ksi.springbooks.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] matchersList = {"/new_book","/edit_book/**", "/delete_book/**",
                "/new_category","/edit_category/**", "/delete_category/**",
                "/new_publisher","/edit_publisher/**", "/delete_publisher/**"};
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(matchersList)
                         .authenticated()
                         .anyRequest().permitAll()
                         )
                         .formLogin(Customizer.withDefaults());
                         return http.build();
                         }
    @Bean
    UserDetailsService userDetailsService(){
        UserDetails user = User.builder()
                .username("user1")
                .password("{noop}passu1")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
