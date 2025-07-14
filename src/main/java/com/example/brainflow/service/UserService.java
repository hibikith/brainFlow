package com.example.brainflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.brainflow.entity.User;
import com.example.brainflow.repository.UserRepository;

@Service 
public class UserService {

    // UserRepositoryを自動注入し、データベース操作に利用
    @Autowired
    private UserRepository userRepository;

    // パスワードのハッシュ化や検証を行うPasswordEncoderを自動注入
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 全てのユーザーを取得する。
     * @return 全ユーザーのリスト
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 新しいユーザーをデータベースに保存する。
     * パスワードは保存前にハッシュ化され、作成日時が自動設定される。
     * @param user 保存するユーザー情報
     * @return 保存されたユーザーオブジェクト
     */
    public User saveUser(User user) {
        // ユーザーのパスワードをハッシュ化して安全に保存
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        // ユーザー作成日時を現在時刻に設定
        user.setCreatedAt(LocalDateTime.now());
        // データベースにユーザーを保存
        return userRepository.save(user);
    }

    /**
     * 指定されたIDのユーザーを検索する。
     * @param id 検索するユーザーのID
     * @return ユーザーが見つかった場合はUserを含むOptional、見つからない場合は空のOptional
     */
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * ユーザー名またはメールアドレスのどちらか一方を使用してユーザーを検索する。
     * ログイン時など、どちらの識別子でも検索できるように利用する。
     * @param identifier ユーザー名またはメールアドレス
     * @return ユーザーが見つかった場合はUserを含むOptional、見つからない場合は空のOptional
     */
    public Optional<User> findByUsernameOrEmail(String identifier) {
        // userRepository.findByUsernameOrEmail() メソッドに同じ識別子を渡し、
        // ユーザー名またはメールアドレスのいずれかに一致するユーザーを検索する
        return userRepository.findByUsernameOrEmail(identifier, identifier);
    }
}