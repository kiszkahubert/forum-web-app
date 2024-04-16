package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ThreadDto {
    private String topic;
    private String textMessage;
    private Timestamp threadDatetime;
    private List<String> tagValues;
}
