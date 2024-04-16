package com.kiszka.forumapp.dataretrieval.dbmanipulation.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseDto {
    private String responseText;
    private Timestamp responseDatetime;
    private int likeCounter;
}
