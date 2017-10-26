package com.yada.wechat.config;

import com.github.wxpay.sdk.WXPayConfig;
import java.io.*;

public class MyConfig implements WXPayConfig{

    private byte[] certData;

    public MyConfig() throws Exception {
        String certPath = "/Users/lihongye/certs/apiclient_cert.p12";
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public String getAppID() {
        return "wxde41f5a91e1a7b02";
    }

    public String getMchID() {
        return "1486523692";
    }

    public String getKey() {
        return "4gmfiig9gdqp2lecrzkn4afi4dx8z8v3";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}

