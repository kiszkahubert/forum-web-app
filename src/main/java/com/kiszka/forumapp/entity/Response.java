package com.kiszka.forumapp.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "response")
public class Response {
    @Id
    @Column(name = "responseId")
    private int responseId;
    @Column(name = "responseText")
    private String responseText;
    @Column(name = "responseDatetime")
    private Timestamp responseDatetime;
    @Column(name = "likeCounter")
    private int likeCounter;

    @ManyToOne
    @JoinColumn(name = "forumUser_userId")
    private ForumUser forumUser;

    @ManyToOne
    @JoinColumn(name = "thread_threadId")
    private Thread thread;

}
