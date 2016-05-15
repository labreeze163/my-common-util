package com.test;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hzzhaolong on 2016/3/9.
 */
public class MD5Util {
    /**
     * @param str
     * @return
     * @Description:  MD5
     */
    public static String parseStrToMd5L32(String str){
        String reStr = null;
        if(StringUtils.isEmpty(str)) {
            return StringUtils.EMPTY;
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b & 0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reStr;
    }

    public static void main(String[] args) {
        System.out.println(parseStrToMd5L32("21920160428cmbchina-mall"));
    }



}
