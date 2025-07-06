package com.example.bookStorApi.security;

import com.example.bookStorApi.service.user.UserServiceRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
/**
 * A custom Spring Security filter to check the client's IP address against an allowlist.
 * This filter extends OncePerRequestFilter to ensure it's executed only once per request.
 */

@Configuration
public class CustomizedIpAddressFiltering extends OncePerRequestFilter {
    private final UserServiceRepository userServiceRepository;

    // Inject userServiceRepository dependency
    CustomizedIpAddressFiltering(UserServiceRepository userServiceRepository){
        this.userServiceRepository = userServiceRepository;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    String clientIpAddress = getClientIpAddress(request);
    if(isIpAddressMatch(clientIpAddress)){
        filterChain.doFilter(request, response);
    }else{
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write("Sorry, You are not member!, please sign up");
         }

    }


    // Extract all existed client ip from the database
    private final List<String> ipAddresses(){
        return userServiceRepository.getAllClientIps();
    }



    // return the ip address of the client
    private String getClientIpAddress(HttpServletRequest request){
        String clientIP = request.getHeader("X-FORWARDED-FOR");
        if(clientIP == null || clientIP.isEmpty() || "unknown".equalsIgnoreCase(clientIP))
            return request.getRemoteAddr();
        return clientIP.split(",")[0];
    }

    // Check if received ip matches with existed ones
    // In case of matching returns true, otherwise false
    private boolean isIpAddressMatch(String ipAddress){
        for(String ip : ipAddresses()){
            IpAddressMatcher matcher = new IpAddressMatcher(ip);
            if(matcher.matches(ipAddress)){
                return  true;
            }
        }
        return false;
    }
}
