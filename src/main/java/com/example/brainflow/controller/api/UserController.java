package com.example.brainflow.controller.api; // パッケージ名を 'controller.api' に変更

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.brainflow.entity.User;
import com.example.brainflow.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * すべてのユーザーを取得するAPIエンドポイント。
     * GET /api/users
     * @return 全ユーザーのリスト
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * 特定のIDを持つユーザーを取得するAPIエンドポイント。
     * GET /api/users/{id}
     * @param id ユーザーID
     * @return ユーザー情報（見つからない場合は404 Not Found）
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * 新しいユーザーを登録するAPIエンドポイント。
     * POST /api/users
     * @param user 登録するユーザー情報
     * @return 登録されたユーザー情報とHTTPステータス (成功時は201 Created、重複時は409 Conflict、エラー時は500 Internal Server Error)
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // ユーザー名またはメールアドレスが重複している場合のエラーハンドリング
            if (e.getMessage().contains("username")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("ユーザー名が既に存在します。");
            } else if (e.getMessage().contains("email")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("メールアドレスが既に登録されています。");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("登録に失敗しました。データが重複している可能性があります。");
        } catch (Exception e) {
            // その他の予期せぬエラーハンドリング
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("サーバー内部エラーが発生しました。");
        }
    }

    /**
     * 特定のIDを持つユーザー情報を更新するAPIエンドポイント。
     * PUT /api/users/{id}
     * @param id 更新対象のユーザーID
     * @param userDetails 更新するユーザー情報
     * @return 更新されたユーザー情報（見つからない場合は404 Not Found）
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> userOptional = userService.findUserById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // ユーザー名とメールアドレスのみを更新する（パスワードは専用のAPIで更新することが推奨される）
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            User updatedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 特定のIDを持つユーザーを削除するAPIエンドポイント。
     * DELETE /api/users/{id}
     * @param id 削除対象のユーザーID
     * @return HTTPステータス (成功時は204 No Content、見つからない場合は404 Not Found)
     * @Note このメソッドは現在、ユーザーが存在するかどうかのみを確認し、実際の削除処理は呼び出していない。
     * UserServiceにdeleteUserメソッドを追加し、ここで呼び出す必要がある。
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            // TODO: ここに userService.deleteUser(id); を追加して、実際の削除処理を行う
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}