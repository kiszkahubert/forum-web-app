package com.kiszka.forumapp.dataretrieval.validation;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.Response;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.Thread;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "forumUser")
public class ForumUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "forumUser_ID_SEQ")
    @SequenceGenerator(name = "forumUser_ID_SEQ",sequenceName = "forumUser_ID_SEQ",allocationSize = 1)
    @Column(name = "userid")
    private int userId;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;
    private String role;
    @OneToMany(mappedBy = "forumUser")
    private List<Response> responses;
    @OneToMany(mappedBy = "forumUser")
    private List<Thread> threads;
}
