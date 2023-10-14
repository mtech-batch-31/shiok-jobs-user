package com.mtech.sjmsuser.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SnsService {

    @Autowired
    private AmazonSNS amazonSNS;

    @Value("${aws.sns.topic.arn.job-seeker-status-update}")
    private String topicArn;

    public void sendMessageToSnsTopic(String message) {
        PublishRequest request = new PublishRequest(topicArn, message);
        amazonSNS.publish(request);
    }
}