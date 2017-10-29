package com.yada.wechat.stream;

import com.yada.wechat.task.WechatTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Map;

@EnableBinding(PlanTaskProcessor.class)
public class PlanTaskConsumer {

    private static Logger logger = LoggerFactory.getLogger(PlanTaskConsumer.class);

    private WechatTask wechatTask;

    @Autowired
    public PlanTaskConsumer(WechatTask wechatTask) {
        this.wechatTask = wechatTask;
    }

    @StreamListener(PlanTaskProcessor.INPUT)
    public void listener(Map<String, Object> map) throws Exception {
        String type = (String) map.get("type");
        if (PlanTaskConstants.EXEC_WECHAT_QUERY.equals(type)) {
            Map<String, Object> payload = (Map<String, Object>) map.get("payload");
            wechatTask.doQuery(payload);
        }
    }
}

