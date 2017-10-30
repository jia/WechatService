package com.yada.wechat.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
@EnableConfigurationProperties(WechatProperties.class)
public class WechatConfigImpl implements WXPayConfig{

    private WechatProperties wechatProperties;

    private byte[] certData;

    @Autowired
    public WechatConfigImpl(WechatProperties wechatProperties) throws Exception{
        this.wechatProperties = wechatProperties;
        String certPath = wechatProperties.getCertPath();
        //读取到相对路径下的证书
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(certPath);
        File file = new File(url.getFile());
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
        return new ByteArrayInputStream(this.certData);
    }

    public int getHttpConnectTimeoutMs() {
        return wechatProperties.getHttpConnectTimeoutMs();
    }

    public int getHttpReadTimeoutMs() {
        return wechatProperties.getHttpReadTimeoutMs();
    }

}

