package com.yada.wechat.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatServiceTest {

    @Autowired
    private WechatService service;

    @Test
    public void unifiedOrder() throws Exception {

        Map<String,String> map = new HashMap<String,String>();
        map.put("key", "value");


    }

    @Test
    public void orderQuery() throws Exception {
    }

    @Test
    public void refund() throws Exception {
    }

    @Test
    public void payNotify() throws Exception {
    }

    @Test
    public void refundNotify() throws Exception {
    }

}