package com.spring.dao;

import com.spring.model.Commodity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by hzzhaolong on 2016/3/9.
 */
@Repository
public interface CommodityDao {

    /**
     * 获取商品最新报价
     * @return
     */
    List<Commodity> getPriceByDate(Date date);


    List<Commodity> getPriceByDateByCatalog(Map<String, String> params);

    /**
     * 根据id获取商品信息
     * @param id
     * @return
     */
    Commodity getCommodityById(int id);

    void updateCommodity(Commodity commodity);

    /**
     * 更新每日报价
     * @param id
     * @param price
     * @param unit
     * @param date
     */
    void updateDailyPrice(
            @Param("id")int id,
            @Param("price")BigDecimal price,
            @Param("unit")String unit,
            @Param("date")Date date);
    /**
     * 新增每日报价
     * @param id
     * @param price
     * @param unit
     * @param date
     */
    void addDailyPrice(
            @Param("id")int id,
            @Param("price")BigDecimal price,
            @Param("unit")String unit,
            @Param("date")Date date);

    /**
     * 判断商品某天的报价是否已维护
     * @param id
     * @param date
     * @return
     */
    int checkDailyPriceExist(
            @Param("id") int id,
            @Param("date") Date date);

}
