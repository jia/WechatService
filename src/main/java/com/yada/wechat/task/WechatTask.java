package com.yada.wechat.task;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatTask {

    private WechatService wechatService;
    private WechatProducer wechatProducer;
    private PlanTaskProducer planTaskProducer;

    @Autowired
    public WechatTask(WechatService wechatService,WechatProducer wechatProducer){
        this.wechatService = wechatService;
        this.wechatProducer = wechatProducer;
        this.planTaskProducer = planTaskProducer;
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

            Map<String, Object> payload = new HashMap<String, Object>();
            payload.put("params", map);
            // TODO 第一次间隔多久执行
            payload.put("waitTime", 500 * 1000);
            payload.put("type", PlanTaskConstants.COMPLETED);

            Map<String, Object> task = new HashMap<String, Object>();
            task.put("type", PlanTaskConstants.CREATE);
            task.put("payload", payload);
            planTaskProducer.send(task);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Async
    public void doQuery(Map<String,String> reqData) throws Exception {

        //TODO 获取时间
        Long executedTimes = (Long) reqData.get("executedTimes");

        try {

            // 判断是否执行查询
            if (executedTimes > 5) {
                //TODO 撤销
            }else {

                // 调用Service并得到响应
                Map<String,String> map = wechatService.orderQuery(reqData);
                // 发送完成事件
                wechatProducer.send(new Event(WechatConstants.REFUND_APPLIED, map));
            }

            Map<String, Object> task = new HashMap<String, Object>();
            task.put("type", PlanTaskConstants.COMPLETED);
            task.put("payload", reqData);
            planTaskProducer.send(task);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
