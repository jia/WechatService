package com.yada.wechat.service;

import com.github.wxpay.sdk.WXPay;
import com.yada.wechat.config.MyConfig;
import com.yada.wechat.stream.Event;
import com.yada.wechat.stream.EventConstants;
import com.yada.wechat.stream.WechatProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Component
public class WechatService {

    private WechatProducer wechatProducer;

    @Autowired
    public WechatService(WechatProducer wechatProducer){
        this.wechatProducer = wechatProducer;
    }

    private static Logger logger = LoggerFactory.getLogger(WechatService.class);

    /**
     * 统一下单
     */
    public Map<String, String> unifiedOrder(Map<String,String> reqData) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        //使用沙箱环境:请调用getsignkey生成沙箱密钥
        //WXPay wxpay = new WXPay(config, WXPayConstants.SignType.MD5, true);
        Map<String, String> resp =null;

        logger.info("data: "+reqData);
        try {
            resp = wxpay.unifiedOrder(reqData);
            logger.info("resp: "+resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    /**
     * 查询订单
     */
    public Map<String, String> orderQuery(Map<String,String> reqData) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> resp =null;

        logger.info("data: "+reqData);
        try {
            resp = wxpay.orderQuery(reqData);
            logger.info("resp: "+resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    /**
     * 申请退款
     */
    public Map<String, String> refund(Map<String,String> reqData) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
        Map<String, String> resp =null;
        logger.info("data:  "+reqData);
        try {
            resp = wxpay.refund(reqData);
            logger.info("resp： "+resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    /**
     * 统一下单通知
     */
    public String payNotify(Map<String,String> reqData) throws Exception {

        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        if (wxpay.isPayResultNotifySignatureValid(reqData)) {
            // 发送统一下单通知type和payload
            wechatProducer.send(new Event(EventConstants.REFUND_APPLIED,reqData));
            return "success";
        }
        else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
            return "fail";
        }
    }

    /**
     * 申请退款通知
     */
    public String refundNotify(@RequestBody Map<String,String> reqData) throws Exception {

        logger.info("refund_notify:  " + reqData);
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        if (wxpay.isPayResultNotifySignatureValid(reqData)) {
            // 发送退款通知type和payload
            wechatProducer.send(new Event(EventConstants.REFUND_APPLIED,reqData));
            // 签名正确
            return "success";
        } else {
            // 签名错误
            return "fail";
        }
    }

}
