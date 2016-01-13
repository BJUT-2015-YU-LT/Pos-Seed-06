package com.company;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Item {
    private String key;  //商品索引
    private String barcode;  //条形码
    private String name;     //商品名称
    private String unit;     //商品单位
    private Double price;    //商品价格
    private int isDiscount;
    private Double discount=1.0; //打折信息
    private int isPromotion;
    private Double vipDiscount;
    private int amount;  //相同物品数量

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public int getIsDiscount() {
        return isDiscount;
    }
    public void setIsDiscount(int isDiscount) {
        this.isDiscount = isDiscount;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getIsPromotion() {
        return isPromotion;
    }
    public void setIsPromotion(int isPromotion) {
        this.isPromotion = isPromotion;
    }

    public double getVipDiscount() {
        return vipDiscount;
    }
    public void setVipDiscount(Double vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
