package com.test.baobaoshu;


import com.alibaba.fastjson.JSONObject;
import com.test.MD5Util;
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
public class BaoBaoShuTest {
    static final String APP_KEY = "10009";
    static final String TOKEN = "e8322074a84f461fa632c689c58bf11f";
    static final String ADDRESS = "http://180.168.13.171:28241/";

    public static void main(String[] args) throws IOException {

        String version = "v1.0";
        String timestamp = "20160312152500";
        String sign = MD5Util.parseStrToMd5L32(APP_KEY + TOKEN + timestamp + version);
        System.out.println(sign);

        String requestUrl = ADDRESS + "storage/adjust"+ "?appkey=" + APP_KEY + "&version=" + version + "&timestamp=" + timestamp + "&sign=" + sign;
        HttpPost httpRequst = new HttpPost(requestUrl);

        JSONObject params = new JSONObject();
        params.put("barcode", "702002676");
        params.put("inventory", "123");
//        params.put("stockNo", "11");
        System.out.println(params.toString());

        httpRequst.setHeader("Content-Type", "application/json");
        httpRequst.setEntity(new StringEntity(params.toString(), "UTF-8"));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        CloseableHttpResponse httpResponse = httpclient.execute(httpRequst);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        HttpEntity httpEntity = httpResponse.getEntity();
        String result = EntityUtils.toString(httpEntity, "UTF-8");// 取出应答字符串
        System.out.println(result);
    }
}
