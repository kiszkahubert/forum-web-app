package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import com.kiszka.forumapp.dataretrieval.dbmanipulation.thread.ThreadService;
import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResponseServiceImpl implements ResponseService {
    private final ForumUserService forumUserService;
    private final ThreadService threadService;

    @Autowired
    public ResponseServiceImpl(ForumUserService forumUserService, ThreadService threadService) {
        this.forumUserService = forumUserService;
        this.threadService = threadService;
    }

    @Override
    public void saveResponse(ResponseDto responseDto) {
        Response response = new Response();
        response.setResponseText(responseDto.getResponseText());
        response.setResponseText(responseDto.getResponseText());
        response.setResponseDatetime(new Timestamp(System.currentTimeMillis()));
        response.setLikeCounter(0);
        response.setForumUser(forumUserService.getCurrentUser());
//        response.setThread(threadService.returnCurrentThread()); //TODO tu trzeba pobrac REST API ze strony jej id
    }
}
