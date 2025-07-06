package com.example.bookStorApi.service.user;

import com.example.bookStorApi.dto.UserDTO;
import com.example.bookStorApi.exceptions.UserNotFoundException;
import com.example.bookStorApi.repository.UserRepository;
import com.example.bookStorApi.user.User;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceRepositoryImpl implements UserServiceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceRepositoryImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;


    @Override
    public void addNewUser(UserDTO userDTO) {


        if(userDTO.getUsername().isEmpty() ||
        userDTO.getPassword().length() < 8 ||
        userDTO.getAddress().length() < 12 ||
        !userDTO.getEmail().contains("@")  ||
        userDTO.getFullName().length() < 8 ||
        !userDTO.getPhone().startsWith("09")  ||
        userDTO.getIp().length() < 4 ){
            LOGGER.info("invalid values");

        }else {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setAddress(userDTO.getAddress());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setFullName(userDTO.getFullName());
            user.setState(userDTO.getState());
            user.setIp(userDTO.getIp());
            LOGGER.info("new user is ready to be added");
            userRepository.save(user);
        }

    }
    // Find user by it ip address
    @Override
    public UserDTO getUserByIpAddress(String ip) {
        Optional<User> userByIpAddress = Optional.ofNullable(Optional.ofNullable(userRepository.findUserByIp(ip))
                .orElseThrow(() -> new UserNotFoundException("no such user exists")));
        return modelMapper.map(userByIpAddress, UserDTO.class);
    }

    // delete user by it id
    @Override
    public void deleteUserByUserId(long id) {
        Optional<User> userById = Optional.ofNullable(userRepository.findUserById(id));
        if(userById.isEmpty())
            LOGGER.info("no such user existed with this id : {}", id);
        LOGGER.info("user deleted successfully");
        userRepository.deleteUserById(id);

    }

    @Override
    public List<String> getAllClientIps() {
        return userRepository.findAllIps();
    }
}
