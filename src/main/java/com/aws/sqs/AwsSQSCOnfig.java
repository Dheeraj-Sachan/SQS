package com.aws.sqs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSQSCOnfig {
    @Value("${Access.key.ID}")
    private String awsAccessKeyId;

    @Value("${Secret.access.key}")
    private String awsSecretKeyId;

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    @Primary
    public QueueMessagingTemplate queueMessagingTemplate() {
        System.out.println(awsAccessKeyId+" ..........        "+awsSecretKeyId);
        return new QueueMessagingTemplate(AmazonSQSAsyncClientBuilder.standard().withRegion("ap-south-1")
                .withCredentials
                        (new AWSStaticCredentialsProvider(new BasicAWSCredentials
                                (awsAccessKeyId, awsSecretKeyId))).build());
    }


}
