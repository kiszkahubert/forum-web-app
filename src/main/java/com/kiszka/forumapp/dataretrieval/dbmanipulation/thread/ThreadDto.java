package com.kiszka.forumapp.dataretrieval.dbmanipulation.thread;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThreadDto {
    @Size(max = 100)
    private String topic;
    @Size(max = 1000)
    private String textMessage;
    private String tags;
}
