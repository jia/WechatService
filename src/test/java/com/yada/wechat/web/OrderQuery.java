package com.yada.wechat.web;

public class OrderQuery {

    private String out_trade_no;

    public OrderQuery(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
