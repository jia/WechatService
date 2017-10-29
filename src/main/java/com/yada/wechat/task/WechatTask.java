package com.yada.wechat.task;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.Event;
import com.yada.wechat.stream.WechatConstants;
import com.yada.wechat.stream.WechatProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WechatTask {

    private WechatService wechatService;
    private WechatProducer wechatProducer;

    @Autowired
    public WechatTask(WechatService wechatService,WechatProducer wechatProducer){
        this.wechatService = wechatService;
        this.wechatProducer = wechatProducer;
    }

    @Async
    public void doUnifiedOrder(Map<String,String> reqData) throws Exception {

        try {
            // 调用Service并得到响应
            Map<String,String> map = wechatService.unifiedOrder(reqData);
            // 发送完成事件
            wechatProducer.send(new Event(WechatConstants.UNIFIED_ORDER_APPLIED, map));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async
    public void doRefund(Map<String,String> reqData) throws Exception {

        try {
            // 调用Service并得到响应
            Map<String,String> map = wechatService.refund(reqData);
            // 发送完成事件
            wechatProducer.send(new Event(WechatConstants.REFUND_APPLIED, map));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async
    public void doQuery(Map<String,String> reqData) throws Exception {



        try {
            // 调用Service并得到响应
            Map<String,String> map = wechatService.orderQuery(reqData);
            // 发送完成事件
            wechatProducer.send(new Event(WechatConstants.REFUND_APPLIED, map));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
