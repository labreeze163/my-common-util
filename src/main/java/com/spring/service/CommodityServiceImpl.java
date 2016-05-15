package com.spring.service;


import com.spring.dao.CommodityDao;
import com.spring.model.Commodity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by hzzhaolong on 2016/3/9.
 */
@Service("commodityService")
public class CommodityServiceImpl  implements CommodityService {

    private static final Logger logger = Logger.getLogger(CommodityServiceImpl.class);

    @Autowired
    CommodityDao commodityDao;


    public List<Commodity> getTheLatestPrice() {

        logger.debug("获取最新报价");

        Date date = new Date(System.currentTimeMillis());
        return getPriceByDate(date);
    }


    public List<Commodity> getPriceByDate(Date date) {
        logger.debug("获取" + date + "日报价");
        return commodityDao.getPriceByDate(date);
//        return new ArrayList<Commodity>();
    }



    public Commodity getCommodityInfo(int id) {
        return commodityDao.getCommodityById(id);
    }




    public void modifyCommodityInfo(Commodity commodity) {
//        boolean b = false;
//        if(b) {
            updateCommodityCatalog(commodity);
//        }
        commodity.setName("name222");
        commodityDao.updateCommodity(commodity);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    private void updateCommodityCatalog(Commodity commodity) {
        commodity.setCatalog("catalog222222222");
        commodityDao.updateCommodity(commodity);
        throw new RuntimeException("1111");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void udpateCommodityInfo(Commodity commodity) {
        commodityDao.updateCommodity(commodity);
//        boolean b = true;
//        if(b) {
//            throw new RuntimeException();
//        }
    }

    public void updateDailyPrice(int id, BigDecimal price, String unit, Date date) {

        //判断当日报价是否存在
        if (commodityDao.checkDailyPriceExist(id, date) != 0) {//存在，更新
            commodityDao.updateDailyPrice(id, price, unit, date);
        } else {//不存在，插入
            commodityDao.addDailyPrice(id, price, unit, date);
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ctx.getBean("commodityDao", CommodityDao.class).checkDailyPriceExist(1, new Date(System.currentTimeMillis()));
    }


}