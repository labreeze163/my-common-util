package com.test;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hzzhaolong on 2016/3/22.
 */
public class XiupinTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\xiupin.txt")), "GBK"));
        String line = reader.readLine().trim();
        StringBuilder builder = new StringBuilder("select thirdpart_order_id,gorder_id  from tb_thirdpart_order where gorder_id in (");
        int n = 0;
        while(StringUtils.isNotEmpty(line)) {
            n++;
            if(n >= 600 && n < 800)
              builder.append("'" + line + "',");
            line = reader.readLine();
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(");");
        System.out.println(builder);
        System.out.println(n);


        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


    }
}
