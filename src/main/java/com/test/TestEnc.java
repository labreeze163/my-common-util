package com.test;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by hzzhaolong on 2016/4/12.
 */
public class TestEnc {

    public static final String APP_KEY = "appkey";
    public static final String SECRET_KEY = "secretkey";

    public static void main(String args[]) {
        String[] envs = {"testin", "online"};
        //20160316
        //String[] batch = {"200","201","202","203","204","205","206","207","208","209"};
        String[] batch = {"201605120001"};

        for (int i = 0; i < batch.length; i++) {
            for(int j = 0; j < envs.length; j++){
                String appKey = Encrypt(batch[i] + envs[j] + APP_KEY, "");
                String secretKey = Encrypt(batch[i] + envs[j] + SECRET_KEY, "");
                System.out.println(batch[i] + "-" + envs[j] + " >>> " + appKey + " " + secretKey);
            }
        }

    }

    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
