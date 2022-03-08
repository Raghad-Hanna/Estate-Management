package com.raghad.estate.asynchronous_messaging;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String content;
    private String emailAddress;
    private Date date;
}
