package com.company;

/**
 * Created by Lyn on 2016/1/12.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnect {
    Connection conn = null;
    String sql;
    String url = "jdbc:mysql://localhost:3306/commoditydata?"
            + "user=root&password=&useUnicode=true&characterEncoding=UTF8";
    PreparedStatement statement1;
    MyWindow mywindow;

    public MySQLConnect(MyWindow myWindow){
        try {
            mywindow=myWindow;
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载MySQL驱动程序");
            mywindow.printlog("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(url);
            mywindow.printlog("成功连接数据库");
            //Statement stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Item findgoods(String name){
        Item item=new Item();
        sql = "select * from commodity where commodity.name='"+name+"'";
        try {
            statement1 = conn.prepareStatement(sql);
            ResultSet rs = statement1.executeQuery(sql);
            while (rs.next()) {
                mywindow.printlog("在数据库中检索到"+rs.getString(3)+"该商品");
                item.setBarcode(rs.getString(2));
                item.setName(rs.getString(3));
                item.setUnit(rs.getString(4));
                item.setPrice(rs.getDouble(5));
                item.setIsDiscount(rs.getInt(6));
                item.setDiscount(rs.getDouble(7));
                item.setIsPromotion(rs.getInt(8));
                item.setVipDiscount(rs.getDouble(9));
            }
            if(item.getName()==null){
                mywindow.printlog("在数据库中未检索到该商品");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public List<Item> findAllgoods(){
        sql = "select * from commodity";
        List<Item> items = new ArrayList<Item>();
        try {
            statement1 = conn.prepareStatement(sql);
            ResultSet rs = statement1.executeQuery(sql);
            while (rs.next()) {
                Item item=new Item();
                item.setBarcode(rs.getString(2));
                item.setName(rs.getString(3));
                item.setUnit(rs.getString(4));
                item.setPrice(rs.getDouble(5));
                item.setIsDiscount(rs.getInt(6));
                item.setDiscount(rs.getDouble(7));
                item.setIsPromotion(rs.getInt(8));
                item.setVipDiscount(rs.getDouble(9));
                items.add(item);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Vipmember findVip(String cardno){
        Vipmember member = new Vipmember();
        sql="select * from vipcard where vipcard.card_no='"+cardno+"'";
        try {
            statement1 = conn.prepareStatement(sql);
            ResultSet rs = statement1.executeQuery(sql);
            while (rs.next()) {
                mywindow.printlog("在数据库中匹配到"+rs.getString(2)+"该会员,"+"可使用优惠!!!!!!");
                member.setCardNo(rs.getString(2));
                member.setName(rs.getString(3));
                member.setPoints(rs.getDouble(4));
            }
            if(member.getName()==null){
                mywindow.printlog("在数据库中未匹配到该会员");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }
}
