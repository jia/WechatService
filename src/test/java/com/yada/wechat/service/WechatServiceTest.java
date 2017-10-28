package com.yada.wechat.service;

import com.github.wxpay.sdk.WXPay;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatServiceTest {

    @Autowired
    private WechatService wechatService;

    @MockBean
    private WXPay wxPay;

    @Test
    public void unifiedOrder() throws Exception {
        Map<String,String> data = new HashMap<String,String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "2016090910595900000012");
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");

        Map<String,String>res= null;

        try {
        BDDMockito.given(wxPay.unifiedOrder(data)).willReturn(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,String> resq = wechatService.unifiedOrder(data);
        Assert.assertEquals("SUCCESS",resq.get("result_code"));

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