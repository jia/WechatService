package com.yada.wechat.config;

import com.github.wxpay.sdk.WXPay;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WechatProperties.class)
public class WechatConfiguration {

    private WechatConfigImpl wechatConfig;

    public WechatConfiguration(WechatConfigImpl wechatConfig){
        this.wechatConfig = wechatConfig;
    }

    @Bean
    public WXPay getWxpay(){
        return new WXPay(wechatConfig);
    }

}
