package com.yada.wechat.task;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.Event;
import com.yada.wechat.stream.PlanTaskProducer;
import com.yada.wechat.stream.WechatProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WechatTask.class})
public class WechatTaskTest {

    @Autowired
    private WechatTask wechatTask;

    @MockBean
    private WechatService wechatService;

    @MockBean
    private WechatProducer wechatProducer;

    @MockBean
    private PlanTaskProducer planTaskProducer;


    @Test
    public void doUnifiedOrder() throws Exception {

        Map<String, String> resSuccess = new HashMap<String, String>();
        resSuccess.put("return_code", "SUCCESS");

        Map<String, String> resFail = new HashMap<String, String>();
        resFail.put("return_code", "FAIL");

        BDDMockito.given(wechatService.unifiedOrder(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn(resSuccess).willReturn(resFail);

        Map<String, String> payload = new HashMap<String, String>();
        wechatTask.doUnifiedOrder(payload);
        wechatTask.doUnifiedOrder(payload);

        BDDMockito.verify(wechatProducer, times(2))
                .send(BDDMockito.any(Event.class));
        BDDMockito.verify(planTaskProducer, times(1))
                .send(BDDMockito.anyMapOf(String.class, Object.class));

    }

    @Test
    public void doRefund() throws Exception {

        Map<String, String> resSuccess = new HashMap<String, String>();
        resSuccess.put("return_code", "SUCCESS");

        BDDMockito.given(wechatService.refund(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn(resSuccess);

        Map<String, String> payload = new HashMap<String, String>();
        wechatTask.doRefund(payload);

        BDDMockito.verify(wechatProducer, times(1)).send(BDDMockito.any(Event.class));
    }

    @Test
    public void doQuery() throws Exception {

        Map<String, String> cancelSuccess = new HashMap<String, String>();
        cancelSuccess.put("return_code", "SUCCESS");

        Map<String, String> cancelFail = new HashMap<String, String>();
        cancelFail.put("return_code", "FAIL");

        BDDMockito.given(wechatService.closeOrder(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn(cancelSuccess).willReturn(cancelFail);

        Map<String, String> querySuccess = new HashMap<String, String>();
        querySuccess.put("trade_no", "trade_no");

        Map<String, String> queryFail = new HashMap<String, String>();
        queryFail.put("trade_no", "trade_no");

        BDDMockito.given(wechatService.orderQuery(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn(querySuccess).willReturn(queryFail);

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("executedTimes", 1);


        wechatTask.doQuery(payload);
        wechatTask.doQuery(payload);
        wechatTask.doQuery(payload);

        payload.put("executedTimes", 6);
        wechatTask.doQuery(payload);

        BDDMockito.verify(wechatService, times(1)).closeOrder(BDDMockito.anyMapOf(String.class, String.class));
        BDDMockito.verify(wechatService, times(3)).orderQuery(BDDMockito.anyMapOf(String.class, String.class));
        BDDMockito.verify(wechatProducer, times(3)).send(BDDMockito.any(Event.class));
        BDDMockito.verify(planTaskProducer, times(1)).send(BDDMockito.anyMapOf(String.class, Object.class));

    }

}