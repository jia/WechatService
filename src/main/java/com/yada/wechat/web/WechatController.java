package com.yada.wechat.web;

import com.yada.wechat.service.WechatService;
import com.yada.wechat.stream.WechatProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WechatController {

    private static Logger logger = LoggerFactory.getLogger(WechatController.class);
    private WechatProducer wechatProducer;

    @Autowired
    private WechatService wechatService;

    @Autowired
    public WechatController(WechatProducer wechatProducer){
        this.wechatProducer = wechatProducer;
    }

    /**
     * 查询订单
     */
    @RequestMapping("/orderQuery")
    public Map<String, String> orderQuery(@RequestBody Map<String,String> reqData) throws Exception {

        return wechatService.orderQuery(reqData);
    }

    /**
     * 微信支付通知
     */
    @RequestMapping("/pay_notify")
    public String payNotify(@RequestBody Map<String,String> reqData) throws Exception {

        return wechatService.payNotify(reqData);
    }

    /**
     * 微信退款通知
     */
    @RequestMapping("/refund_notify")
    public String refundNotify(@RequestBody Map<String,String> reqData) throws Exception {

       return wechatService.refundNotify(reqData);
    }
}
