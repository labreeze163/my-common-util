package com.test.zhaoshang;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by hzzhaolong on 2016/4/20.
 */
public class ZhaoshangTest1 {
    public static void main(String[] args) {
        String url = "https://api.xxx.com/api/mall.api/sellerapi/getorder.json";
        HttpPost httpRequst = new HttpPost(url);// 创建HttpPost对象

        long time = System.currentTimeMillis();
        String source = "1021";
        String sign_method = "md5";
        String appKey = "4527c6cbed1077fbb768839f2068932455728709d9f92948fb40a677567e4a96";
        String appSecret = "f6e0dfc25289c2aa86dceee176adbd8e81069ae7990e314fcafc84fb6e9914c6";
        String goodsID = "1129305";
        String skuId = "1129305-348de14747bdfd6ed15de42c09e50622";
        String accountId = "testforvip299@163.com";

        TreeMap<String, String> parameterMap = new TreeMap<String, String>();
        parameterMap.put("timestamp", new Timestamp(time).toString());
        parameterMap.put("v", "1.0");
        parameterMap.put("sign_method", sign_method);
        parameterMap.put("app_key", appKey);
        parameterMap.put("source", source);
        List<Map<String, Object>> orderItemList = new ArrayList<Map<String, Object>>();
        // 构造商品信息
        Map<String, Object> orderItemMap = new HashMap<String, Object>();
        orderItemMap.put("goodsId", goodsID);
        orderItemMap.put("skuId", skuId);
        orderItemMap.put("buyAmount", 1);
        orderItemList.add(orderItemMap);
        JSONObject orderItemJsonObject = new JSONObject();
        orderItemJsonObject.put("orderItemList", orderItemList);
        parameterMap.put("orderItemList", orderItemJsonObject.toString());
        // 构造用户信息
        Map<String, Object> userInfoMap = new HashMap<String, Object>();
        userInfoMap.put("accountId", accountId);
        userInfoMap.put("name", "虞圆浩");
        userInfoMap.put("mobile", "13857479955");
        userInfoMap.put("email", "8545646@11.com");
        userInfoMap.put("provinceName", "浙江省");
        userInfoMap.put("cityName", "宁波市");
        userInfoMap.put("districtName", "北仑区");
        userInfoMap.put("address", "新碶街道北仑世茂世界湾二期3幢201室");
        userInfoMap.put("identityName", "虞圆浩");
        userInfoMap.put("identityId", "330206198210113436");
        JSONObject userInfoJsonObject = new JSONObject();
        userInfoJsonObject.put("userInfo", userInfoMap);
        parameterMap.put("userInfo", userInfoJsonObject.toString());


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("timestamp", new Timestamp(time).toString()));
        params.add(new BasicNameValuePair("v", "1.0"));


        try {
            httpRequst.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpclient.execute(httpRequst);
            System.out.println(httpResponse.getStatusLine().getStatusCode());
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);// 取出应答字符串

            System.out.println("[Thirdpart Order][result] " + result);

        } catch (UnsupportedEncodingException e) {

        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        } catch (Exception e) {

        }
        System.out.println("end...");





    }



}



