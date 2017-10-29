package com.yada.wechat.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(WechatProcessor.class)
public class WechatProducer {

    private WechatProcessor wechatProcessor;

    @Autowired
    public WechatProducer(WechatProcessor wechatProcessor) {
        this.wechatProcessor = wechatProcessor;
    }

    public boolean send(Event event) {
        return wechatProcessor.output().send(new GenericMessage<Event>(event));
    }
}
