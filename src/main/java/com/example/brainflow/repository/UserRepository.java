package com.example.brainflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.brainflow.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// JpaRepossitoryを継承し、Userエンティティを扱い、主キーはLong
	
	// カスタムメソッドの生成
	// (findBy + プロパティ名)でSpring Data JPAが対応するSQLクエリを自動生成。
	
	// メールアドレスでユーザーを検索するメソッド
    Optional<User> findByEmail(String email);

    // ユーザー名でユーザーを検索するメソッド
    Optional<User> findByUsername(String username);

    // ユーザー名またはメールアドレスでユーザーを検索するメソッド
    // OR条件で検索し、両方のフィールドに対して同じ識別子（identifier）を渡すことを想定
    Optional<User> findByUsernameOrEmail(String username, String email);
}


