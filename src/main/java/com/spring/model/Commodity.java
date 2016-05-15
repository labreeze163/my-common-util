package com.spring.model;
import java.math.BigDecimal;
import java.sql.Date;
/**
 * Created by hzzhaolong on 2016/3/9.
 */
public class Commodity {

    private int id;

    /**
     * 公众号ID
     */
    private long appId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品种类
     */
    private String catalog;

    /**
     * 商品简介
     */
    private String summary;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品缩略图
     */
    private String mainUrl;

    /**
     * 商品详情图片
     */
    private String detailUrls;

    /**
     * 拓展配置
     */
    private String extConfig;

    //////////
    private BigDecimal price;
    private String unit;
    private Date priceDate;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(Date priceDate) {
        this.priceDate = priceDate;
    }

    //////////


    public String getExtConfig() {
        return extConfig;
    }

    public void setExtConfig(String extConfig) {
        this.extConfig = extConfig;
    }

    public String getDetailUrls() {
        return detailUrls;
    }

    public void setDetailUrls(String detailUrls) {
        this.detailUrls = detailUrls;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
