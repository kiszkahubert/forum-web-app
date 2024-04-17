package com.kiszka.forumapp.dataretrieval.validation;

import java.util.List;

public interface ForumUserService {
    void saveUser(ForumUserDto forumUserDto);
    ForumUser findUserByEmail(String email);
    ForumUser getCurrentUser();
}
