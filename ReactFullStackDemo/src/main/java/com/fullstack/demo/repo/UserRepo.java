package com.fullstack.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.demo.model.UserDb;

@Repository
public interface UserRepo extends JpaRepository<UserDb, Long> {

}
