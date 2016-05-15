package com.spring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hzzhaolong on 2016/3/15.
 */
//@Component
public class TestProperties {

    @Value("#{testConfigProperties['bbs.appkey']}")
    private String appKey;

//    @Value("${bbss.appsecret}")
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
