package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl extends ResponseService{
    ForumUserService forumUserService;

    @Autowired
    public ResponseServiceImpl(ForumUserService forumUserService) {
        this.forumUserService = forumUserService;
    }

    @Override
    public void saveResponse() {
        Response response = new Response();

    }
}
