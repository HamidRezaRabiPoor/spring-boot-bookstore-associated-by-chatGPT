package com.example.bookStorApi.service.user;

import com.example.bookStorApi.dto.UserDTO;

import java.util.List;

public interface UserServiceRepository {

    void addNewUser(UserDTO userDTO);

    UserDTO getUserByIpAddress(String ip);

    void deleteUserByUserId(long id);

    List<String> getAllClientIps();

}
