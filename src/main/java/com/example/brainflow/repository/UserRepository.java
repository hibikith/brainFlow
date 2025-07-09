package com.example.brainflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.brainflow.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
