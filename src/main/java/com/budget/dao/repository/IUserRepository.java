package com.budget.dao.repository;

import com.budget.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT u FROM User u")
    List<User> findAll();

    @Query("select case when count(u)>0 then true else false end from User u where u.username = :login")
    boolean checkIfUserExists(@Param("login") String login);

    @Query("select case when count(u)>0 then true else false end from User u where u.mail = :mail")
    boolean checkIfMailExists(@Param("mail") String mail);
}
