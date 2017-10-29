package com.yada.wechat.stream;

import com.yada.wechat.task.WechatTask;
import com.yada.wechat.utils.ObjToMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Map;

@EnableBinding(WechatProcessor.class)
public class WechatConsumer {

    private WechatTask wechatTask;
    private static Logger logger = LoggerFactory.getLogger(WechatConsumer.class);


    @Autowired
    public WechatConsumer(WechatTask wechatTask) { this.wechatTask = wechatTask; }

    @StreamListener(WechatProcessor.INPUT)
    public void listener(Map<String,String> map) throws Exception {

        if (WechatConstants.UNIFIED_ORDER.equals(map.get("type"))){
            //listener到统一下单的事件
            wechatTask.doUnifiedOrder(ObjToMap.objMap(map.get("payload")));

        }else if(WechatConstants.REFUND.equals(map.get("type"))){
            //监听到退款的事件
            wechatTask.doRefund(ObjToMap.objMap(map.get("payload")));
        }
    }

}

