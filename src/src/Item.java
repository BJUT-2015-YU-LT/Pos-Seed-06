/**
 * Created by Administrator on 2016/1/5.
 */
public class Item {
    private String barcode;  //条形码
    private String name;     //商品名称
    private String unit;     //商品单位
    private double price;    //商品价格
    private double discount=1.0; //打折信息


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

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
