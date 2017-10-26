package com.yada.wechat.contorller;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.yada.wechat.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    /**
     * 统一下单
     */
    @RequestMapping("/unifiedorder")
    public Map<String, String> unifiedOrder(@RequestBody Map<String,String> reqData) throws Exception {

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
     * 申请退款
     */
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public Map<String, String> refund(@RequestBody Map<String,String> reqData) throws Exception {

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
     * 微信支付通知
     */
    @RequestMapping("/pay_notify")
    public String payNotify(@RequestBody Map<String,String> notifyData) throws Exception {

//        String notifyData = "...."; // 支付结果通知的xml格式数据
//
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);
//
//        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);  // 转换成map

        if (wxpay.isPayResultNotifySignatureValid(notifyData)) {
            // 签名正确
            // TODO 进行签名正确后的处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
            return "success";
        }
        else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
            return "fail";
        }
    }

    /**
     * 微信退款通知
     */
    @RequestMapping("/refund_notify")
    public String refundNotify(@RequestBody Map<String,String> reqData) throws Exception {

        logger.info("refund_notify:  "+reqData);
//        MyConfig config = new MyConfig();
//        WXPay wxpay = new WXPay(config);
//        Map<String, String> resp =null;

        if (reqData.get("result_code") == "SUCCESS" && reqData.get("return_msg") == "OK") {
            // 返回码成功
            // TODO 返回码成功的处理。
            return "success";
        }
        else {
            return "fail";
        }
    }

}
