package com.example.brainflow.controller.web; // パッケージ名を 'controller.web' に変更

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.brainflow.service.UserService; 

@Controller 
public class WebController {

    @Autowired // UserServiceを自動的に注入し、ビジネスロジックを利用する
    private UserService userService; 

    @GetMapping("/") // ルートURL ( http://localhost:8080/) へのGETリクエストを処理
    public String index() {
        return "index"; // src/main/resources/templates/index.html を返す
    }

    @GetMapping("/register") // /register へのGETリクエストを処理
    public String register() {
        return "register"; // src/main/resources/templates/register.html を返す
    }

    @GetMapping("/login") // /login へのGETリクエストを処理
    public String login() {
        return "login"; // src/main/resources/templates/login.html を返す
    }

    @GetMapping("/dashboard") // /dashboard へのGETリクエストを処理
    public String dashboard() {
        return "dashboard"; // src/main/resources/templates/dashboard.html を返す
    }
}