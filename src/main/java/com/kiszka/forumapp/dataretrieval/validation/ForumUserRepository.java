package com.kiszka.forumapp.dataretrieval.validation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumUserRepository extends JpaRepository<ForumUser,Integer> {
    ForumUser findByEmail(String email);
    ForumUser findByRole(String role);
}
