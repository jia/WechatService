package com.yada.wechat.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(PlanTaskProcessor.class)
public class PlanTaskProducer {

    private PlanTaskProcessor planTaskProcessor;

    @Autowired
    public PlanTaskProducer(PlanTaskProcessor planTaskProcessor) {
        this.planTaskProcessor = planTaskProcessor;
    }

    public void send(Event event) {
        planTaskProcessor.output().send(new GenericMessage<Event>(event));
    }
}