package com.kiszka.forumapp.validation;

import com.kiszka.forumapp.entity.ForumUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ForumUser,Integer> {
    ForumUser findByEmail(String email);
    ForumUser findByRole(String role);
}
