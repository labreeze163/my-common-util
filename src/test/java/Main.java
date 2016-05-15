import java.util.Arrays;

/**
 * Created by hzzhaolong on 2016/3/14.
 */
public class Main {

    public static class  parent{

        public String a = "a";
    }

    public static class  SON extends parent {
        public String b = "b";
    }

    public static parent process(parent p) {
        p.a = "aaa";
        return  p;
    }

    public static void main(String[] args) {
        parent p = new SON();
        process(p);
        System.out.println(p);
    }


}
