package com.yada.wechat.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@EnableConfigurationProperties(WechatProperties.class)
public class WechatConfigImpl implements WXPayConfig{

    private WechatProperties wechatProperties;

    @Autowired
    public WechatConfigImpl(WechatProperties wechatProperties){
        this.wechatProperties = wechatProperties;
    }

    private byte[] certData;

    public WechatConfigImpl() throws Exception {
        String certPath = wechatProperties.getCertPath();
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return wechatProperties.getAppid();
    }

    public String getMchID() {
        return wechatProperties.getMch_id();
    }

    public String getKey() {
        return wechatProperties.getKey();
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return wechatProperties.getHttpConnectTimeoutMs();
    }

    public int getHttpReadTimeoutMs() {
        return wechatProperties.getHttpReadTimeoutMs();
    }

}

