package com.kiszka.forumapp.validation;

import com.kiszka.forumapp.entity.ForumUser;

import java.util.List;

public interface UserService {
    void saveUser(ForumUserDto forumUserDto);
    ForumUser findUserByEmail(String email);
    ForumUser getCurrentUser();
    List<ForumUserDto> findAllUsers();
    ForumUser getAdmin();
}
