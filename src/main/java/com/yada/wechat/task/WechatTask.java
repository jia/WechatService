package com.yada.wechat.task;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class WechatTask {

    private WechatService wechatService;
    private WechatProducer wechatProducer;
    private PlanTaskProducer planTaskProducer;

    @Autowired
    public WechatTask(WechatService wechatService,WechatProducer wechatProducer,PlanTaskProducer planTaskProducer){
        this.wechatService = wechatService;
        this.wechatProducer = wechatProducer;
        this.planTaskProducer = planTaskProducer;
    }

    @Value("${planTask.startTime}")
    private int planTaskStartTime;
    @Value("${planTask.waitTime}")
    private int planTaskWaitTime;
    @Value("${planTask.executedTimes}")
    private int planTaskExecutedTimes;

    @Async
    public void doUnifiedOrder(Map<String,String> reqData) throws Exception {

        try {
            // 调用Service并得到响应
            Map<String,String> resq = wechatService.unifiedOrder(reqData);
            // 发送完成事件
            wechatProducer.send(new Event(WechatConstants.UNIFIED_ORDER_APPLIED, resq));

            if("SUCCESS".equals(resq.get("return_code")) ){
                Map<String, Object> taskPayload = new HashMap<String, Object>();
                taskPayload.put("params", resq);
                long currentTime = new Date().getTime();
                String executedTimes = reqData.get("executedTimes");
                System.out.println("time: "+executedTimes);
                Long waitTimeByExecutedTimes ;
                if (executedTimes != null){
                    if(Long.valueOf(executedTimes) == 1){
                        System.out.println("type1");
                        waitTimeByExecutedTimes = currentTime+15 * 60 * 1000;
                    }else if (Long.valueOf(executedTimes) == 2){
                        System.out.println("type2");
                        waitTimeByExecutedTimes = currentTime+30 * 60 * 1000;
                    }else if (Long.valueOf(executedTimes) == 3 ){
                        System.out.println("type3");
                        waitTimeByExecutedTimes = currentTime+180 * 60 * 1000;
                    }else if (Long.valueOf(executedTimes) == 4 || Long.valueOf(executedTimes) == 5|| Long.valueOf(executedTimes) == 6|| Long.valueOf(executedTimes) == 7){
                        waitTimeByExecutedTimes = currentTime+1800 * 60 * 1000;
                    }else if (Long.valueOf(executedTimes) == 8){
                        waitTimeByExecutedTimes = currentTime+3600 * 60 * 1000;
                    }else {
                        waitTimeByExecutedTimes = currentTime + planTaskStartTime;
                    }
                }else {
                    waitTimeByExecutedTimes = currentTime + planTaskStartTime;
                }
                System.out.println("waitTimeByExecutedTimes"+waitTimeByExecutedTimes);
                taskPayload.put("startTime", currentTime + planTaskStartTime);
                taskPayload.put("waitTime", waitTimeByExecutedTimes);
                taskPayload.put("type", PlanTaskConstants.COMPLETED);

                Map<String, Object> taskEvent = new HashMap<String, Object>();
                taskEvent.put("type", PlanTaskConstants.CREATE);
                taskEvent.put("payload", taskPayload);
                planTaskProducer.send(taskEvent);
            }

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
    public void doQuery(Map<String,Object> reqData) throws Exception {

        int executedTimes = (Integer) reqData.get("executedTimes");
        System.out.println("time: "+executedTimes);
        Map<String, String> params = (Map<String, String>) reqData.get("params");

        try {
            if (executedTimes > planTaskExecutedTimes){
//                Map<String, String> map = alipayService.cancel(params);
                // 撤销查询
                Map<String, String> map = wechatService.closeOrder(params);

                if("SUCCESS".equals(map.get("return_code"))){
                    Map<String, Object> task = new HashMap<String, Object>();
                    task.put("type", PlanTaskConstants.COMPLETED);
                    task.put("payload", reqData);
                    planTaskProducer.send(task);
                }

            }else {
                // 调用Service并得到响应
                Map<String,String> map = wechatService.orderQuery(params);
                // 发送完成事件
                wechatProducer.send(new Event(WechatConstants.REFUND_APPLIED, map));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
