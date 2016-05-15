package com.test.baobaoshu;


import com.alibaba.fastjson.JSONArray;
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
public class BaoBaoShuTest2 {
    static final String APP_KEY = "10009";
    static final String TOKEN = "e8322074a84f461fa632c689c58bf11f";
    static final String ADDRESS = "http://180.168.13.171:28241/";

    public static void main(String[] args) throws IOException {

        String version = "v6.0";
        String timestamp = "20160312222500";
        String sign = MD5Util.parseStrToMd5L32(APP_KEY + TOKEN + timestamp + version);
        System.out.println(sign);

        String requestUrl = ADDRESS + "item/pushItemInfo"+ "?appkey=" + APP_KEY + "&version=" + version + "&timestamp=" + timestamp + "&sign=" + sign;
        HttpPost httpRequst = new HttpPost(requestUrl);

        JSONObject params = new JSONObject();
        params.put("spuName", "testspu77");
        params.put("smallId", 11);
        params.put("brandName", "testbrand");
        params.put("unitId", 7);
        params.put("remark", "备注");
        params.put("itemDesc", "xxx");
        params.put("descMobile", "test descmobile");

        // 组装itemAttr
        JSONArray itemAttrs = new JSONArray();
//        JSONObject itemAttr = new JSONObject();
//        itemAttr.put("attrKey", "testkey0");
//        itemAttr.put("attrValue", "testvalue0");
//        itemAttrs.add(itemAttr);
        params.put("itemAttr", itemAttrs);

        // 组装ItemPictures
        JSONArray itemPictures = new JSONArray();
        JSONObject itemPicture = new JSONObject();
        itemPicture.put("path", "http://pic.qiushibaike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
        JSONObject itemPicture2 = new JSONObject();
        itemPicture2.put("path", "http://pic.qiushibaike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
        JSONObject itemPicture3 = new JSONObject();
        itemPicture3.put("path", "http://pic.qiushibaike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
        itemPictures.add(itemPicture);
        itemPictures.add(itemPicture2);
        itemPictures.add(itemPicture3);
        params.put("itemPictures", itemPictures);

        // 组装ItemDetails
        JSONArray itemDetails = new JSONArray();
        JSONObject itemDetail = new JSONObject();
        itemDetail.put("mainTitle", "testMaintile0");
        itemDetail.put("subTitle", "testSubtitle0");
        itemDetail.put("storageTitle", " ");
        itemDetail.put("barcode", "ttestbarvcaode200");
        itemDetail.put("manufacturer", "testManufacturer");
        itemDetail.put("basicPrice", 11.1);
//        itemDetail.put("saleRate", 1);
//        itemDetail.put("purRate", 1);
        itemDetail.put("wavesSign", 1);
        itemDetail.put("tarrif_rate", 0.3);
        itemDetail.put("countryId", 11);
        itemDetail.put("returnDays", 11);
        itemDetail.put("expSign", 1);
//        itemDetail.put("expDays", 30);
        itemDetail.put("qualityGoods", 1);
        itemDetail.put("weight", 11);
//        itemDetail.put("weightNet", 10);
        itemDetail.put("volumeWidth", 11);
        itemDetail.put("volumeLength", 11);
        itemDetail.put("volumeHigh", 11);
//        itemDetail.put("remark", "备注");
//        itemDetail.put("ageStartKey", 1);
//        itemDetail.put("ageEndKey", 10);
//        itemDetail.put("inventory", 11);
//        itemDetail.put("stockNo", 11);
//        itemDetail.put("salesPrice", 111);

        // 组装PostPrice
//        JSONArray posterPics = new JSONArray();
//        JSONObject posterPic = new JSONObject();
//        posterPic.put("path", "http://pic.qiushibaike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
//        JSONObject posterPic2 = new JSONObject();
//        posterPic2.put("path", "http://pic.qiush2ibaike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
//        JSONObject posterPic3 = new JSONObject();
//        posterPic3.put("path", "http://pic.qiushi3baike.com/system/pictures/11528/115285176/medium/app115285176.jpg");
//        posterPics.add(posterPic);
//        posterPics.add(posterPic2);
//        posterPics.add(posterPic3);
//        itemDetail.put("posterPics", posterPics);
//
//        // specInfosDesc
        JSONArray specInfosDescs = new JSONArray();
//        JSONObject specInfosDesc = new JSONObject();
//        specInfosDesc.put("specGroupName", "1");
//        specInfosDesc.put("specName", "1");
//        specInfosDescs.add(specInfosDesc);
        itemDetail.put("specInfosDesc", specInfosDescs);
        itemDetails.add(itemDetail);
        params.put("itemdeail", itemDetails);

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
