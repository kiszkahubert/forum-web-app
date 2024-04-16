package com.kiszka.forumapp.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "thread")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "thread_ID_SEQ")
    @SequenceGenerator(name = "thread_ID_SEQ",sequenceName = "thread_ID_SEQ",allocationSize = 1)
    @Column(name = "threadId")
    private int threadId;
    @Column(name = "topic")
    private String topic;
    @Column(name = "textMessage")
    private String textMessage;
    @Column(name = "threadDateTime")
    private Timestamp threadDatetime;
    @Column(name = "likeCounter")
    private int LikeCounter;
    @ManyToOne
    @JoinColumn(name = "forumUser_userId")
    private ForumUser forumUser;
    @OneToMany(mappedBy = "thread")
    private List<Response> responses;
    @OneToMany(mappedBy = "thread")
    private List<Tag> tags;

}
