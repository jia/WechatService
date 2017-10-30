package com.yada.wechat.stream;

public class Event {

    public Event(String type, Object payload) {
        this.type = type;
        this.payload = payload;
    }

    private String type;

    private Object payload;

    public String getType() {
        return type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public Object getPayload() {
        return payload;
    }

//    public void setPayload(Object payload) {
//        this.payload = payload;
//    }
}
