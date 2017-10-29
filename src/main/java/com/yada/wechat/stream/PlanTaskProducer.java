package com.yada.wechat.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.GenericMessage;

import java.util.Map;

@EnableBinding(PlanTaskProcessor.class)
public class PlanTaskProducer {

    private PlanTaskProcessor planTaskProcessor;

    @Autowired
    public PlanTaskProducer(PlanTaskProcessor planTaskProcessor) {
        this.planTaskProcessor = planTaskProcessor;
    }

    public boolean send(Map<String, Object> event) {
        return planTaskProcessor.output().send(new GenericMessage<Map<String, Object>>(event));
    }}