package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import com.kiszka.forumapp.dataretrieval.validation.ForumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ResponseServiceImpl implements ResponseService {
    ForumUserService forumUserService;

    @Autowired
    public ResponseServiceImpl(ForumUserService forumUserService) {
        this.forumUserService = forumUserService;
    }

    @Override
    public void saveResponse(ResponseDto responseDto) {
        Response response = new Response();
        response.setResponseText(responseDto.getResponseText());
        response.setResponseText(responseDto.getResponseText());
        response.setResponseDatetime(new Timestamp(System.currentTimeMillis()));
    }
}
