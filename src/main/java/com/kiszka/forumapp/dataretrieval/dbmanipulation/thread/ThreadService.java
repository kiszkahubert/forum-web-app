package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import java.util.List;

public interface ThreadService {
    void createThread(ThreadDto threadDto);
    List<Thread> getAllUserThreads();
    Thread returnCurrentThread(int id);
    List<Thread> getAllThreads();
}
