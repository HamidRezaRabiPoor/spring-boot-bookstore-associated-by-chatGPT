package com.example.bookStorApi.repository;

import com.example.bookStorApi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);

    @Query(value = "select u.id, u.address, u.email, u.fullName, u.ip, u.phone," +
            " u.state, u.username from user u where u.ip = ?1")
    User findUserByIp(String ip);

    @Query("delete from user u where u.id = ?1")
    void deleteUserById(long id);

    @Query(value = "select u.id, u.address, u.email, u.fullName, u.ip, u.phone," +
            " u.state, u.username from user u where u.id = ?1")
    User findUserById(long id);

}
