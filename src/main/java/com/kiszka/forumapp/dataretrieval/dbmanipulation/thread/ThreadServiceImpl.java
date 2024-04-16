package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import com.kiszka.forumapp.dataretrieval.validation.ForumUser;
import com.kiszka.forumapp.dataretrieval.dbmanipulation.Tag;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThreadServiceImpl implements ThreadService {
    private final ThreadRepository threadRepository;
    ForumUserService forumUserService;

    @Autowired
    public ThreadServiceImpl(ForumUserService forumUserService, ThreadRepository threadRepository) {
        this.forumUserService = forumUserService;
        this.threadRepository = threadRepository;
    }

    @Override
    public void createThread(ThreadDto threadDto) {
        ForumUser currentUser = forumUserService.getCurrentUser();
        Thread thread = new Thread();
        thread.setTopic(thread.getTopic());
        thread.setTextMessage(thread.getTextMessage());
        thread.setThreadDatetime(new Timestamp(System.currentTimeMillis()));
        thread.setForumUser(currentUser);
        thread.setLikeCounter(0);
        List<Tag> tags = new ArrayList<>();
        for(String tagValue : threadDto.getTagValues()){
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
        return threadRepository.findAllBy(forumUserService.getCurrentUser().getEmail());
    }
}
