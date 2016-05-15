package com.test.baobaoshu;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hzzhaolong on 2016/3/18.
 */
public class BBSContryTest {

    public static void main(String[] args) throws IOException {


        Map<String, Long> maps = new HashMap<String, Long>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\bbs-country.txt")), "GBK"));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\bbs-countryid.txt")), "GBK"));
        String line = reader.readLine().trim();
        String line2 = reader2.readLine().trim();
        while (StringUtils.isNotEmpty(line)) {
            maps.put(line, Long.parseLong(line2));
            line = reader.readLine();
            line2 = reader2.readLine();
            if (StringUtils.isNotEmpty(line2)) line2 = line2.trim();
        }
        System.out.println(maps.size());

        Map<String, Long> countryMap = new HashMap<String, Long>();
        List<String> notExistsCoutny = new ArrayList<String>();
        int count = 0;
        BufferedReader reader3 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("e:\\kaola-country.txt")), "GBK"));
        String line3 = reader3.readLine();
        while (StringUtils.isNotEmpty(line3)) {
            line3 = reader3.readLine();
            if (StringUtils.isNotEmpty(line3)) line3 = line3.trim();
            if (maps.get(line3) != null) {
                count++;
                System.out.println("map.put(\"" + line3 + "\", " + maps.get(line3) +"L);");
            } else {
                notExistsCoutny.add(line3);
            }
        }

        System.out.println("Not Find--------------------------------------");
        for(String str : notExistsCoutny) {
            System.out.println(str);
        }

        System.out.println(count);
    }


}
