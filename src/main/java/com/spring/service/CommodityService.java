package com.spring.service;

import com.spring.model.Commodity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by hzzhaolong on 2016/3/9.
 */
@Service
public interface CommodityService {

    /**
     * 最新报价
     * @return
     */
    List<Commodity> getTheLatestPrice();


    /**
     * 获取某天的报价
     * @param date
     * @return
     */
    List<Commodity> getPriceByDate(Date date);

    /**
     * 获取单个商品信息
     * @param id
     * @return
     */
    Commodity getCommodityInfo(int id);

    /**
     * 修改商品信息
     * @param commodity
     */
    void modifyCommodityInfo(Commodity commodity);

    /**
     * 修改商品信息
     * @param commodity
     */
    void udpateCommodityInfo(Commodity commodity);

    /**
     * 更新每日报价
     * @param id
     * @param price
     * @param unit
     * @param date
     */
    @Transactional(readOnly = false)
    void updateDailyPrice(int id, BigDecimal price, String unit, Date date);
}
