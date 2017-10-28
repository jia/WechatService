package com.yada.wechat.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Map;

@EnableBinding(PlanTaskProcessor.class)
public class PlanTaskConsumer {

    private static Logger logger = LoggerFactory.getLogger(PlanTaskConsumer.class);

    @StreamListener(PlanTaskProcessor.INPUT)
    public void listener(Map<String, Object> map) {
        String type = (String) map.get("type");
    }
}
