package com.example.bookStorApi.tools;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ConfigTools {


    // mapper; to map objects.
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    // To encrypt passwords
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
