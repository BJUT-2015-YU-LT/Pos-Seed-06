package com.company;
import java.text.DecimalFormat;
import java.util.*;
/**
 * Created by Administrator on 2016/1/5.
 */
public class output {
    public void Print() {
// TODO Auto-generated method stub
        int size ;
        DecimalFormat df=new DecimalFormat("######0.00");
        Double TotalPrice=0.0;
        Double cPrice=0.0;
        Double JS=0.0;
        String js;
        Input input=new Input();
        ArrayList<Item> ItemList=input.ReturnIndexList();
        ArrayList<Item> ItemList2=input.ReturnList();
        size=ItemList.size();
        Map<String, Double> map;
        map = TransToMap(ItemList,ItemList2);
        Set<String> set = map.keySet();

        System.out.println("***商店购物清单***");
        for (Iterator<String> iter = set.iterator(); iter.hasNext();)
        {
            Item good=new Item();
            String key = (String)iter.next();
            good=getitem(key,ItemList);
            Double value = (Double)map.get(key);
            System.out.println(
                    "名称:   "+good.getName()+"   "+
                    "数量:   "+(int)(value/good.getPrice())+good.getUnit() +"   "+
                    "单价:   "+good.getPrice()+"(元)"+"   "+
                    "小计:   " + value*good.getDiscount()+"(元)");
            cPrice=cPrice+value;
            TotalPrice=TotalPrice+value*good.getDiscount();
        }
        JS=cPrice-TotalPrice;
        js=df.format(JS);
        System.out.println("----------------");
        System.out.println("总计:"+TotalPrice+"(元)");
        System.out.println("节省:"+ js+"(元)");
        System.out.println("**********************");
    }

    //用Map 归类同名商品为一类
    public Map<String, Double> TransToMap(ArrayList<Item> indexlist,ArrayList<Item> goodlist)
    {
        Map<String, Double> map = new LinkedHashMap<String, Double>();
        for (Item exp:goodlist)
        {
            for(Item ind:indexlist)
            {
                if(exp.getBarcode().equals(ind.getBarcode()))
                {
                    if (map.containsKey(exp.getBarcode())) {
                        double price = map.get(exp.getBarcode());
                        map.put(exp.getBarcode(), ind.getPrice() + price);
                    } else {
                        map.put(exp.getBarcode(), ind.getPrice());
                    }
                }
            }
        }
        return map;
    }

    //通过BarCode返回Good
    public Item getitem(String BarCode,ArrayList<Item> list)
    {
        for (Item exp:list){
            if (exp.getBarcode().equals(BarCode)){
                return exp;
            }
        }
        return null;
    }

}
