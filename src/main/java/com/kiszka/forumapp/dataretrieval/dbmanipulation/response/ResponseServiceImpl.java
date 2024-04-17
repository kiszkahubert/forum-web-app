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
    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseServiceImpl(ForumUserService forumUserService, ThreadService threadService, ResponseRepository responseRepository) {
        this.forumUserService = forumUserService;
        this.threadService = threadService;
        this.responseRepository = responseRepository;
    }

    @Override
    public Response saverResponse(Response response) {
        return responseRepository.save(response);
    }
}
