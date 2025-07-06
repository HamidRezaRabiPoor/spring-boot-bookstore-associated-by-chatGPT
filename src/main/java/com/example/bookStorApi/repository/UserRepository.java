package com.example.bookStorApi.repository;

import com.example.bookStorApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Query for find user by it username
    @Query(value = "select u from user u where u.username = ?1")
    User findByUsername(String username);

    // Query for select user by it ip
    @Query(value = "select u from user u where u.ip = ?1")
    User findUserByIp(String ip);

    // Query for delete user by it id
    @Query("delete from user u where u.id = ?1")
    void deleteUserById(long id);

    // Query for select user by it id
    @Query(value = "select u from user u where u.id= ?1")
    User findUserById(long id);

    // select all ip,s from the database
    @Query(value = "select u.ip from user u")
    List<String> findAllIps();

}
