package com.test.baobaoshu;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by hzzhaolong on 2016/3/9.
 */
public class BaoBaoShuTest3 {
    static final String APP_KEY = "10009";
    static final String TOKEN = "e8322074a84f461fa632c689c58bf11f";
    static final String ADDRESS = "http://180.168.13.171:28241/";

    public static void main(String[] args) throws IOException {

        Long a = 3L;
        Long b = 3L;
        System.out.println(a == b);
    }
}
