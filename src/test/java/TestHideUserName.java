/**
 * Created by hzzhaolong on 2016/3/1.
 */
public class TestHideUserName {


    public static String hideUserName(String userName) {
        String defaultUserName = "超人***";
//        if(StringUtils.isEmpty(userName) || userName.lastIndexOf('@') == -1) {
//            return defaultUserName;
//        }
        int index = userName.lastIndexOf('@');
        String prefixName = userName.substring(0, index);
        String suffixName = userName.substring(index);
        if(prefixName.length() < 2) {
            return prefixName + "***" + suffixName;
        } else {
            return prefixName.substring(0, 2) + "***" + suffixName;
        }
    }


    public static void main(String[] args) {
        System.out.println(hideUserName("z@126.com"));
        System.out.println(hideUserName("zh@126.com"));
        System.out.println(hideUserName("我@126.com"));
        System.out.println(hideUserName("我的@126.com"));
        System.out.println(hideUserName("我的妹妹@126.com"));
        System.out.println(hideUserName("亲"));
        System.out.println(hideUserName("你好"));
    }
}
