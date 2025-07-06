package com.example.bookStorApi.security;

import com.example.bookStorApi.repository.UserRepository;
import com.example.bookStorApi.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSecurityFilterChain {


    private final CustomizedIpAddressFiltering customizedIpAddressFiltering;

    // Inject filter class
    public ConfigSecurityFilterChain(CustomizedIpAddressFiltering customizedIpAddressFiltering){
        this.customizedIpAddressFiltering = customizedIpAddressFiltering;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.addFilterBefore(customizedIpAddressFiltering, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
               requests.requestMatchers("/admin").hasRole("ADMIN")
                       .requestMatchers("/user").hasRole("USER")
                       .requestMatchers("/bookstore/**")
                       .permitAll()
                       .anyRequest()
                       .authenticated())
               .formLogin(form ->
                       form.loginPage("/login"))
               .logout(LogoutConfigurer::permitAll);
       return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findByUsername(username);
            if(user != null) return  user;
            throw new UsernameNotFoundException("no such user exists");
        };
    }
}
