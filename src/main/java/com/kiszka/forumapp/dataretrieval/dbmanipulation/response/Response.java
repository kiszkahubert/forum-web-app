package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.Thread;
import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "response_ID_SEQ")
    @SequenceGenerator(name = "response_ID_SEQ",sequenceName = "response_ID_SEQ",allocationSize = 1)
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
