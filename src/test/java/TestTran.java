import com.spring.model.Account;
import com.spring.model.Commodity;
import com.spring.model.TestProperties;
import com.spring.service.AccountService;
import com.spring.service.CommodityService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hzzhaolong on 2016/3/9.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class TestTran {


    @Autowired
    private CommodityService commodityService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TestProperties testProperties;

    static  final Logger log = Logger.getLogger(TestTran.class);


    @Test
    public void test() {
        log.info("test");
        Commodity commodityInfo = commodityService.getCommodityInfo(3);
        commodityService.modifyCommodityInfo(commodityInfo);
        System.out.println(commodityInfo);
    }

    @Test
    public void test2() {
        Commodity commodityInfo = commodityService.getCommodityInfo(3);
        commodityInfo.setName("name111111");
        commodityInfo.setCatalog("catalog1111");
        commodityService.udpateCommodityInfo(commodityInfo);
    }

    @Test
    public void test3() {
        Account account = accountService.getAccountByUserName("laotest1@163.com");
        System.out.println(account);
        account.setUpdateTime(System.currentTimeMillis());
        accountService.insertAccount(account);
    }

    @Test
    public void test4() {
        System.out.println(testProperties.getAppKey() + "," + testProperties.getAppSecret());
    }
}
