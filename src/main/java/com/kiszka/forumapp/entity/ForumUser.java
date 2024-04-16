package com.kiszka.forumapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "forumUser")
public class ForumUser {
    @Id
    @Column(name = "userid")
    private int userId;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "forumUser")
    private List<Response> responses;
    @OneToMany(mappedBy = "forumUser")
    private List<Thread> threads;
}
