package com.project.ohflix._core.utils;

import com.project.ohflix.domain.user.User;

public class DummyObject {

    protected User newUser(String email, String nickname, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setName(name);
        return user;
    }
}
