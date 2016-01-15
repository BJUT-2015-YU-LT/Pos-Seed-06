package com.company;
import org.junit.Test;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;
/**
 * Created by Administrator on 2016/1/5.
 */
public class test {
    @Test
    public void Test()
    {
        Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/commoditydata?"
                + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
        PreparedStatement statement1;
        MyWindow mywindow=new MyWindow("测试");
        mywindow.setMyMenuBar();
        mywindow.startMysqlConnection();
        mywindow.setgoodindex();
        MySQLConnect cs=new MySQLConnect(mywindow);
        ArrayList<Item> GoodList = new ArrayList<Item>();
        Item g1=new Item();
        Item g2=new Item();
        g1.setBarcode("1");
        g1.setName("测试1");
        g1.setAmount(1);
        g1.setUnit("个");
        g1.setIsPromotion(1);
        g1.setKey("suoyin");
        g1.setDiscount(0.9);
        g1.setIsDiscount(1);
        g1.setPrice(5.0);
        g1.setVipDiscount(0.8);
        GoodList.add(g1);
        System.out.println(g1.getAmount());
        System.out.println(g1.getKey());
        System.out.println(g1.getName());
        System.out.println(g1.getPrice());
        System.out.println(g1.getUnit());
        System.out.println(g1.getIsDiscount());
        System.out.println(g1.getDiscount());
        System.out.println(g1.getIsPromotion());
        System.out.println(g1.getVipDiscount());
        System.out.println("--------------------------------------------------------------");
        Vipmember v1=new Vipmember();
        v1.setName("会员");
        v1.setCardNo("id1029013901");
        v1.setPoints(20);
        System.out.println(v1.getName());
        System.out.println(v1.getCardNo());
        System.out.println(v1.getPoints());
        System.out.println("--------------------------------------------------------------");
        g2=cs.findgoods("可口可乐");
        System.out.println(g2.getAmount());
        System.out.println(g2.getKey());
        System.out.println(g2.getName());
        System.out.println(g2.getPrice());
        System.out.println(g2.getUnit());
        System.out.println(g2.getIsDiscount());
        System.out.println(g2.getDiscount());
        System.out.println(g2.getIsPromotion());
        System.out.println(g2.getVipDiscount());
        System.out.println("--------------------------------------------------------------");
        g1=cs.findgoods("dasd");
        List<Item> items = new ArrayList<Item>();
        items=cs.findAllgoods();
        for(Item item:items)
        {
            System.out.print(item.getAmount()+"  ");
            System.out.print(item.getKey()+"  ");
            System.out.print(item.getName()+"  ");
            System.out.print(item.getPrice()+"  ");
            System.out.print(item.getUnit()+"  ");
            System.out.print(item.getIsDiscount()+"  ");
            System.out.print(item.getDiscount()+"  ");
            System.out.print(item.getIsPromotion()+"  ");
            System.out.print(item.getVipDiscount()+"  ");
            System.out.println("\n");
        }
        System.out.println("--------------------------------------------------------------");
        v1=cs.findVip("MEM000001");
        cs.setPoint("MEM000001",10);
        System.out.println(v1.getName());
        System.out.println(v1.getCardNo());
        System.out.println(v1.getPoints());

        v1=cs.findVip("weqweqwe");
        System.out.println("--------------------------------------------------------------");

        cs.setPoint("MEM000001",10);

    }

    @Test
    public void Testwindow() {
        MyWindow mywindow=new MyWindow("测试");
        mywindow.setMyMenuBar();
        mywindow.startMysqlConnection();
        mywindow.setgoodindex();
    }

    @Test
    public void Testout() {
        int size ;
        DecimalFormat df=new DecimalFormat("######0.00");
        Double TotalPrice=0.0;
        Double cPrice=0.0;
        Double JS=0.0;
        String js;
        Input input=new Input();
        ArrayList<Item> ItemList=input.ReturnIndexList();
        ArrayList<Item> ItemList2=input.ReturnList();
        Item c=new Item();
        size=ItemList.size();
        Map<String, Double> map;


        output a = new output();
        a.Print();
        map=a.TransToMap(ItemList,ItemList2);
        Set<String> set = map.keySet();
        for (Iterator<String> iter = set.iterator(); iter.hasNext();)
        {
            Item good=new Item();
            String key = (String)iter.next();
            Double value = (Double)map.get(key);
            System.out.println(value);
        }
        c=a.getitem("ITEM000000",ItemList);
        System.out.println( c.getName());
    }

}
