package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.Tag;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {
    private final ThreadRepository threadRepository;
    private final ForumUserService forumUserService;

    @Autowired
    public ThreadServiceImpl(ForumUserService forumUserService, ThreadRepository threadRepository) {
        this.forumUserService = forumUserService;
        this.threadRepository = threadRepository;
    }

    @Override
    public void createThread(ThreadDto threadDto) {
        ForumUser currentUser = forumUserService.getCurrentUser();
        Thread thread = new Thread();
        thread.setTopic(threadDto.getTopic());
        thread.setTextMessage(threadDto.getTextMessage());
        thread.setThreadDateTime(new Timestamp(System.currentTimeMillis()));
        thread.setForumUser(currentUser);
        thread.setLikeCounter(0);
        List<Tag> tags = new ArrayList<>();
        for(String tagValue : threadDto.getTags().split(",")){
            Tag tag = new Tag();
            tag.setTagValue(tagValue);
            tag.setThread(thread);
            tags.add(tag);
        }
        thread.setTags(tags);
        threadRepository.save(thread);
    }

    @Override
    public List<Thread> getAllUserThreads() {
        return threadRepository.findAllByForumUser_Email(forumUserService.getCurrentUser().getEmail());
    }

    @Override
    public Thread returnCurrentThread(int id) {
        return threadRepository.findByThreadId(id);
    }

    @Override
    public List<Thread> getAllThreads() {
        return threadRepository.findAll();
    }
}
