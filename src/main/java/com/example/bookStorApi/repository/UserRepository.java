package com.example.bookStorApi.repository;

import com.example.bookStorApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findUserByIp(String ip);

    void deleteUserById(long id);

    User findUserById(long id);

}
