package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreadRepository extends JpaRepository<Thread,Integer> {
    List<Thread> findAllBy(String email);
    Thread findByThreadId(int id);
}
