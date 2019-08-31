package com.destinym.dubbotools.controller;

import com.destinym.dubbotools.vo.Result;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mengliang
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping(path = "/login")
    public Result<Token> login(@RequestBody User user) {
        Token token = new Token();
        token.setToken("admin-token");
        return Result.success(token);
    }

    @PostMapping(path = "/logout")
    public Result<Void> logout(@RequestBody User user) {
        return Result.success(null);
    }

    @RequestMapping("/info")
    public Result<User> info() {
        User user = new User();
        user.setName("admin");
        user.setToken("admin-token");
        user.setAvatar("/user.png");
        return Result.success(user);
    }

    @Data
    public static class User {
        private String password;
        private String name;
        private String token;
        private String avatar;

    }

    @Data
    public static class Token {
        private String token;
    }
}
