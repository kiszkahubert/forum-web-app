package com.kiszka.forumapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "tagId")
    private int tagId;
    @Column(name = "tagValue")
    private String tagValue;

    @ManyToOne
    @JoinColumn(name = "thread_threadId")
    private Thread thread;

}
