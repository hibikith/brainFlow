package com.example.brainflow;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.brainflow.entity.User;
import com.example.brainflow.repository.UserRepository;

@SpringBootApplication
public class BrainFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrainFlowApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            // テストユーザーを作成
            User newUser = new User();
            newUser.setUsername("testuser");
            newUser.setEmail("test@example.com");
            newUser.setPasswordHash("hashedpassword123"); 
            newUser.setCreatedAt(LocalDateTime.now());

            // ユーザーを保存
            userRepository.save(newUser);
            System.out.println("--- Test user saved: " + newUser.getUsername() + " ---");

            // 保存されたユーザーを検索して表示
            userRepository.findAll().forEach(user -> {
                System.out.println("Found user: " + user.getUsername() + ", Email: " + user.getEmail());
            });
        };
    }

}
