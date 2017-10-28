package com.yada.wechat.stream;

public class WechatConstants {

    //统一下单和申请退款
    public static final String UNIFIED_ORDER = "unifiedorder";
    public static final String REFUND = "refund";

    //统一下单和申请退款响应的事件
    public static final String UNIFIED_ORDER_APPLIED = "unifiedorderApplied";
    public static final String REFUND_APPLIED = "refundApplied";

    //删除对应的计划任务（订单完成，退款完成）
    public static final String ORDER_COMPLETED = "orderCompleted";
    public static final String REFUND_COMPETED = "refundCompleted";

    //统一下单通知和申请退款通知
    public static final String PAY_NOTIFY = "pay_notify";
    public static final String REFUND_NOTIFY = "refund_notify";

}
