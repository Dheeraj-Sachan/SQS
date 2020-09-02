package com.aws.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {
    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @Value("${aws.arn}")
    private String pathToArn;

    public void sendMessage(StringBuffer stringBuffer) {

        System.out.println("In Send Message class ^^^^^^^^^^^^^^");
        System.out.println("Path to SQS " + pathToArn);
        System.out.println("Object creted " + queueMessagingTemplate);

        queueMessagingTemplate.send(pathToArn, MessageBuilder.withPayload(stringBuffer).build());
        queueMessagingTemplate.convertAndSend(pathToArn, "Dheeraj");
    }
}
