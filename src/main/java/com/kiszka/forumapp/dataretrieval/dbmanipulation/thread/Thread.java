package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.response.Response;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.Tag;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
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
    private Timestamp threadDateTime;
    @Column(name = "likeCounter")
    private int LikeCounter;
    @ManyToOne
    @JoinColumn(name = "forumUser_userId")
    private ForumUser forumUser;
    @OneToMany(mappedBy = "thread")
    private List<Response> responses;
    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL)
    private List<Tag> tags;

}
