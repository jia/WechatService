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
        Map<String,String> map = new HashMap<String,String>();

        Map<String,String>res= null;

        try {
        BDDMockito.given(wxPay.unifiedOrder(map)).willReturn(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,String> resq = wechatService.unifiedOrder(map);
        System.out.println("unifiedOrder: "+resq);
        Assert.assertEquals(null, resq);

//        Map<String,String> resSuccess = new HashMap<String,String>();
//        resSuccess.put("code","code");
//        Map<String,String> resFail = new HashMap<String,String>();
//        resSuccess.put("code","code");
//        Map<String,String> reqPayload = new HashMap<String,String>();
//
//        BDDMockito.given(wxPay.unifiedOrder(BDDMockito.anyMapOf(String.class, String.class)))
//                .willReturn(resSuccess).willReturn(resFail);
//
//        Map<String, String> success = wechatService.unifiedOrder(reqPayload);
//
//        Assert.assertEquals(resSuccess.get("code"), success.get("code"));

    }

    @Test
    public void orderQuery() throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        Map<String,String>res= null;

        try {
            BDDMockito.given(wxPay.orderQuery(map)).willReturn(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,String> resq = wechatService.orderQuery(map);
        System.out.println("orderQuery"+resq);
        Assert.assertEquals(null, resq);
    }

    @Test
    public void refund() throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        Map<String,String>res= null;

        try {
            BDDMockito.given(wxPay.refund(map)).willReturn(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,String> resq = wechatService.refund(map);
        System.out.println("refund"+resq);
        Assert.assertEquals(null, resq);
    }

    @Test
    public void payNotify() throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        String resS= "success";
        String resF= "fail";

        try {
            BDDMockito.given(wxPay.isPayResultNotifySignatureValid(map)).willReturn(true).willReturn(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        String resq = wechatService.payNotify(map);
        String resQF = wechatService.payNotify(map);
        System.out.println("refundNotify :"+resq);
        Assert.assertEquals(resS, resq);
        Assert.assertEquals(resF, resQF);
    }

    @Test
    public void refundNotify() throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        String resS= "success";
        String resF= "fail";

        try {
            BDDMockito.given(wxPay.isPayResultNotifySignatureValid(map)).willReturn(true).willReturn(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        String resq = wechatService.refundNotify(map);
        String resQF = wechatService.refundNotify(map);
        System.out.println("refundNotify :"+resq);
        Assert.assertEquals(resS, resq);
        Assert.assertEquals(resF, resQF);
    }

    @Test
    public void closeOrder() throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        Map<String,String>res= null;

        try {
            BDDMockito.given(wxPay.closeOrder(map)).willReturn(res);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,String> resq = wechatService.closeOrder(map);
        System.out.println("closeOrder: "+resq);
        Assert.assertEquals(null, resq);
    }

}