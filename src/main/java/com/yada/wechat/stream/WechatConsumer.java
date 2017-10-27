package com.yada.wechat.stream;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.task.WechatTask;
import com.yada.wechat.utils.ObjToMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import sun.jvm.hotspot.debugger.MachineDescriptionPPC;

import java.util.Map;

@EnableBinding(Sink.class)
public class WechatConsumer {

    private final WechatTask wechatTask;

    @Autowired
    public WechatConsumer(WechatTask wechatTask) { this.wechatTask = wechatTask; }

    @StreamListener(Sink.INPUT)
    public void listener(Event event) throws Exception {

        if (EventConstants.UNIFIED_ORDER.equals(event.getType())){
            //listener到统一下单的事件
            wechatTask.doUnifiedOrder(ObjToMap.objMap(event.getPayload()));

        }else if(EventConstants.REFUND.equals(event.getType())){
            //监听到退款的事件
            wechatTask.doRefund(ObjToMap.objMap(event.getPayload()));
        }
    }

}

