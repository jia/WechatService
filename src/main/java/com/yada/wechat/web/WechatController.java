package com.yada.wechat.web;

import com.github.wxpay.sdk.WXPay;
import com.yada.wechat.config.WechatConfigImpl;
import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.WechatProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);
    private WechatProducer wechatProducer;

    @Autowired
    private WechatService wechatService;

    @Autowired
    WechatConfigImpl myConfig;

    @Autowired
    public WechatController(WechatProducer wechatProducer,WechatConfigImpl myConfig){
        this.wechatProducer = wechatProducer;this.myConfig = myConfig;
    }

    /**
     * 微信支付通知
     */
    @RequestMapping(value = "/pay_notify",method = RequestMethod.POST)
    public String payNotify(@RequestBody Map<String,String> reqData) throws Exception {

        return wechatService.payNotify(reqData);
    }

    /**
     * 微信退款通知
     */
    @RequestMapping(value = "/refund_notify",method = RequestMethod.POST)
    public String refundNotify(@RequestBody Map<String,String> reqData) throws Exception {

       return wechatService.refundNotify(reqData);
    }

//    @RequestMapping(value = "/refund",method = RequestMethod.POST)
//    public Map<String,String> refund(@RequestBody Map<String,String> reqData) throws Exception {
//
//        Map<String, String> resp =null;
//        WXPay wxPay = new WXPay(myConfig);
//
//        logger.info("data: "+reqData);
//        try {
//            resp = wxPay.refund(reqData);
//            logger.info("resp: "+resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return resp;
//    }
//
//    @RequestMapping("/orderQuery")
//    public Map<String, String> orderQuery(@RequestBody Map<String,String> reqData) throws Exception {
//
////        WechatConfigImpl config = new WechatConfigImpl();
//        WXPay wxpay = new WXPay(myConfig);
//        Map<String, String> resp =null;
//
//        logger.info("data: "+reqData);
//        try {
//            resp = wxpay.orderQuery(reqData);
//            logger.info("resp: "+resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return resp;
//    }

}
