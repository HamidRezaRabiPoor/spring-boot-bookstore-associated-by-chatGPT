package com.example.bookStorApi.controller;

import com.example.bookStorApi.dto.UserDTO;
import com.example.bookStorApi.service.user.UserServiceRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bookstore/user")
public class UserController {


    private final HttpServletRequest httpServletRequest;
    private final UserServiceRepository userServiceRepository;


    // Initial http request and service repository
    public UserController(HttpServletRequest httpServletRequest,
                          UserServiceRepository userServiceRepository){
        this.httpServletRequest = httpServletRequest;
        this.userServiceRepository = userServiceRepository;
    }

    @PostMapping("/registry")
    public String registerNewUser(@RequestParam UserDTO userDTO){
        String msg;
        if(!userDTO.objectIsEmpty()){
            userDTO.setIp(httpServletRequest.getRemoteAddr());
            userServiceRepository.addNewUser(userDTO);
            msg = "user registered successfully";
        }else{
            msg = "there is a problem in registering user";
        }
        return msg;
    }
}
