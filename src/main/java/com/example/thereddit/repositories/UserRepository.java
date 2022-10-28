package com.example.thereddit.repositories;

import com.example.thereddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Integer> {
    User findByNameEqualsAndPasswordEquals (@Param("name")String name,@Param("password") String password);
    User findByName (@Param("name")String name);
}
