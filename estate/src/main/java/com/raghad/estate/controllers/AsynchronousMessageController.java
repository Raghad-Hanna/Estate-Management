package com.raghad.estate.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.raghad.estate.asynchronous_messaging.Message;
import com.raghad.estate.asynchronous_messaging.RabbitMQConfiguration;

@RestController
public class AsynchronousMessageController {
    RabbitTemplate template;

    @Autowired
    public AsynchronousMessageController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("api/messages")
    public Message publishMessage(@RequestBody Message message) {
        template.convertAndSend(RabbitMQConfiguration.EXCHANGE_NAME,
                RabbitMQConfiguration.ROUTING_KEY, message);

        return message;
    }
}
