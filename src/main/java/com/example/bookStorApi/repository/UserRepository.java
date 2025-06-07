package com.example.bookStorApi.repository;

import com.example.bookStorApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findUserByIp(String ip);
}
