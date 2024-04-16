package com.kiszka.forumapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "tagId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tag_ID_SEQ")
    @SequenceGenerator(name = "tag_ID_SEQ",sequenceName = "tag_ID_SEQ",allocationSize = 1)
    private int tagId;
    @Column(name = "tagValue")
    private String tagValue;
    @ManyToOne
    @JoinColumn(name = "thread_threadId")
    private Thread thread;

}
