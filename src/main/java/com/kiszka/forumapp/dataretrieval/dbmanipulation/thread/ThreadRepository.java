package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread,Integer> {
    List<Thread> findAllByForumUser_Email(String email);
    Thread findByThreadId(int id);
    List<Thread> findAll();
}
