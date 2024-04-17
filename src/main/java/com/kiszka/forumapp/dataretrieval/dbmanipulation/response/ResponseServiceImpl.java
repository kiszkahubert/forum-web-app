package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    @Override
    public void saveResponse(Response response) {
        responseRepository.save(response);
    }

}
