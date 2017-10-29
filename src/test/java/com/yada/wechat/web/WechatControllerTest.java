package com.yada.wechat.web;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.WechatProducer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatControllerTest {

    @Autowired
    private WechatController wechatController;

    @MockBean
    private WechatProducer wechatProducer;

    @MockBean
    private WechatService wechatService;


    @Test
    public void payNotify() throws Exception {
        BDDMockito.given(wechatService.payNotify(BDDMockito.anyMapOf(String.class, String.class)))
                .willReturn("success").willReturn("fail").willThrow(Exception.class);

        Map<String,String> map = new HashMap<String,String>();
        map.put("nonce_str","iFjt3EuIYp3N3t26");
        map.put("code_url","weixin://wxpay/bizpayurl?pr=e3JtEyP");
        map.put("appid","wxde41f5a91e1a7b02");
        map.put("sign","103154202A03B88C2D24FBEA87A61495");
        map.put("trade_type","NATIVE");
        map.put("return_msg","OK");
        map.put("result_code","SUCCESS");
        map.put("mch_id","1486523692");
        map.put("return_code","SUCCESS");
        map.put("prepay_id","wx20171026151347f89c314bf10037953590");


        String res= "SUCCESS";


        Map<String, String> payload = new HashMap<String, String>();

        payload.put("trade_status", "TRADE_CLOSED");
        Assert.assertEquals("success", wechatController.payNotify(payload));

        payload.put("trade_status", "TRADE_SUCCESS");
        Assert.assertEquals("success", wechatController.payNotify(payload));


    }

    @Test
    public void refundNotify() throws Exception {
    }

}