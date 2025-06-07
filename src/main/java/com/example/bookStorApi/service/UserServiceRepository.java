package com.example.bookStorApi.service;

import com.example.bookStorApi.dto.UserDTO;

public interface UserServiceRepository {

    void addNewUser(UserDTO userDTO);

    UserDTO getUserByIpAddress(String ip);

}
