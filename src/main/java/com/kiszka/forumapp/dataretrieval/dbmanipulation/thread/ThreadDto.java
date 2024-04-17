package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadDto {
    private String topic;
    private String textMessage;
    private String tags;
}
