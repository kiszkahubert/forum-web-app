package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThreadService {
    void createThread(ThreadDto threadDto);
    List<Thread> getAllUserThreads();
    Thread returnCurrentThread(int id);
    List<Thread> getAllThreads();
    Page<Thread> getAllPageThreads(Pageable pageable);
}
