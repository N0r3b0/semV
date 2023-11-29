package ksi.springbooks.security;

import ksi.springbooks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserService userService;
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
        List<ksi.springbooks.models.User> users = userService.findAll();
        UserDetails[] usersDetails = new UserDetails[users.size()];
        for(int i = 0; i < users.size(); i++)
        {
            usersDetails[i] = User.builder()
                    .username(users.get(i).getUsername())
                    .password(users.get(i).getPassword())
                    .roles(users.get(i).getRole())
                    .build();
        }
        return new InMemoryUserDetailsManager(usersDetails[0], usersDetails[1]);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
