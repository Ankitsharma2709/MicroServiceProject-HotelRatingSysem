package com.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.entities.User;

public interface userRepository extends JpaRepository<User, String>{

}
