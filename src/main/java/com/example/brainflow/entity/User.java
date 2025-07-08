package com.example.brainflow.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "username", nullable = false, unique = true, length = 50)
	
	private String username;
	
	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;
	
	@Column(name = "password_hash", nullable = false, length = 255)
	private String passwordHash;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

}

