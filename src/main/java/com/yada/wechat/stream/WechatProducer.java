package com.yada.wechat.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(Source.class)
public class WechatProducer {

    private Source source;

    @Autowired
    public WechatProducer(Source source) {
        this.source = source;
    }

    public void send(Event event) {
        source.output().send(new GenericMessage<Event>(event));
    }
}
