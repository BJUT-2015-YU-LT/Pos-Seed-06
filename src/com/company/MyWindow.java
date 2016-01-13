package com.company;

/**
 * Created by Lyn on 2016/1/12.
 */
import java.awt.*; //JFrame要用到的类
import java.awt.event.*; //事件类
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import javax.swing.*;//包含JFrame
import javax.swing.table.DefaultTableModel;

public class MyWindow {
    MySQLConnect mysql;
    JFrame jframe = new JFrame();//创建对象
    JMenuBar jmb = new JMenuBar();//创建菜单条对象
    JMenu jm1, jm2, jm3;//菜单
    JMenuItem jmi1, jmi2, jmi3, jmi4;//菜单项
    JPanel userinput = new JPanel();
    JScrollPane goodindex = new JScrollPane();
    JScrollPane goodlist = new JScrollPane();
    JScrollPane checkout = new JScrollPane();
    JScrollPane jdbccheck = new JScrollPane();
    JTable goodlist1 = new JTable();
    JTable goodindex1 = new JTable();
    DefaultTableModel tableModel = (DefaultTableModel) goodlist1.getModel();
    DefaultTableModel tableModel2 = (DefaultTableModel) goodindex1.getModel();
    JPanel biggerPanel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JTextArea jtf1,jtf2,jtf3;
    JButton findgood = new JButton("添加商品");
    JButton calculategood = new JButton("结算商品");
    JButton clearcart = new JButton("清空购物车");
    JButton addtocart = new JButton("添加至购物车");
    JTextField barcode = new JTextField();
    List<Item> items = new ArrayList<Item>();


    public MyWindow(String title) {//构造　初始化

        jframe.setTitle(title); //设置标题
        jframe.setSize(1800, 1000);//定义窗口大小
        Container contentPane = jframe.getContentPane();
        biggerPanel.setLayout(new GridLayout(1,3));

        jtf1 = new JTextArea();
        goodindex1.setFont(new Font("宋体",Font.BOLD, 20));
        goodindex1.setRowHeight(20);
        tableModel2.addColumn("商品条形码");
        tableModel2.addColumn("商品名称");
        tableModel2.addColumn("商品单位");
        tableModel2.addColumn("商品单价");
        goodindex.setViewportView(goodindex1);

        goodlist1.setFont(new Font("宋体",Font.BOLD, 20));
        goodlist1.setRowHeight(20);
        tableModel.addColumn("商品条形码");
        tableModel.addColumn("商品名称");
        goodlist.setViewportView(goodlist1);

        jtf3 = new JTextArea();
        checkout.setViewportView(jtf3);

        jtf2 = new JTextArea();
        jtf2.setFont(new Font("宋体",Font.BOLD, 20));
        jdbccheck.setViewportView(jtf2);

       // userinput.setBackground(Color.RED);

        //设置滚动条总是出现
        goodindex.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        goodindex.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        goodlist.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        goodlist.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        checkout.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        checkout.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jdbccheck.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jdbccheck.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //将goodindex加入jpanel并设置绝对布局
        panel1.setLayout(null);
        JLabel label1=new JLabel("商品信息：");
        label1.setBounds(20,20,120,20);
        addtocart.setBounds(400,20,120,20);
        goodindex.setBounds(20,60,500,550);
        panel1.add(label1);
        panel1.add(addtocart);
        panel1.add(goodindex);

        //将goodlist加入jpanel并设置绝对布局
        panel2.setLayout(null);

        JLabel label2=new JLabel("购物车商品列表：");
        label2.setBounds(80,20,120,20);
        clearcart.setBounds(460,20,120,20);
        goodlist.setBounds(80,60,500,550);

        panel2.add(label2);
        panel2.add(clearcart);
        panel2.add(goodlist);

        //将userinput设置绝对布局
        userinput.setLayout(null);
        JLabel label3=new JLabel("收银台");
        label3.setFont(new Font("宋体",Font.BOLD, 61));
        label3.setBounds(200,20,300,100);
        JLabel label4=new JLabel("商品名称：");
        label4.setBounds(50,140,80,20);
        barcode.setBounds(145,140,300,20);
        findgood.setBounds(145,180,300,50);
        calculategood.setBounds(145,550,300,50);
        //barcode.setBounds();
        //findgood.setBounds();
        userinput.add(label3);
        userinput.add(label4);
        userinput.add(barcode);
        userinput.add(findgood);
        userinput.add(calculategood);

        biggerPanel.add(panel1);
        biggerPanel.add(userinput);
        biggerPanel.add(panel2);

        //将checkout加入jpanel并设置绝对布局
        panel3.setLayout(null);
        panel3.setPreferredSize(new Dimension(1800,300));
        JLabel label5=new JLabel("商品结算：");
        label5.setFont(new Font("宋体",Font.BOLD, 61));
        label5.setBounds(100,80,350,100);
        checkout.setBounds(595,0,595,300);
        jdbccheck.setBounds(1275,0,500,300);
        panel3.add(label5);
        panel3.add(checkout);
        panel3.add(jdbccheck);

        //button添加事件



        contentPane.add(biggerPanel, BorderLayout.CENTER);
        contentPane.add(panel3, BorderLayout.SOUTH);
        //检索数据库并添加至购物车
        findgood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(barcode.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "请输入商品名称", "错误代号1", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Item item=mysql.findgoods(barcode.getText());
                if(item.getBarcode()!=null) {
                    String[] rowValues = {
                            item.getBarcode(),item.getName()
                    };
                    tableModel.addRow(rowValues);
                }
            }
        });
        //商品结算
        calculategood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i=0;
                int check=0;
                List<String> cartNo = new ArrayList<String>();
                List<Item> totalgoods = new ArrayList<Item>();
                for(i=0;i<goodlist1.getRowCount();i++){
                    cartNo.add(goodlist1.getValueAt(i,0).toString());
                }
                for(String no:cartNo){
                    System.out.println(no);
                    for(Item item:items){
                        if(item.getBarcode().equals(no)){
                            for(Item totalitem:totalgoods){
                                if(totalitem.getBarcode().equals(no)){
                                    totalitem.setAmount(totalitem.getAmount()+1);
                                    check=1;
                                }
                            }
                            if(check==0){
                                Item item2 = new Item();
                                item2.setBarcode(item.getBarcode());
                                item2.setName(item.getName());
                                item2.setUnit(item.getUnit());
                                item2.setPrice(item.getPrice());
                                item2.setIsDiscount(item.getIsDiscount());
                                item2.setDiscount(item.getDiscount());
                                item2.setIsPromotion(item.getIsPromotion());
                                item2.setVipDiscount(item.getVipDiscount());
                                item2.setAmount(1);
                                totalgoods.add(item2);
                            }
                            check=0;
                        }
                    }
                }
                if(cartNo==null||cartNo.size()==0){
                    JOptionPane.showMessageDialog(null, "购物车为空", "错误代号2", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                jtf3.append("***商店购物清单***"+"\n");
                Double totalmoney=0.0;
                Double realtotalmoney=0.0;
                Date date=new Date();
                DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String time=format.format(date);
                jtf3.append("打印时间："+time+"\n");
                jtf3.append("----------------------"+"\n");
                DecimalFormat df = new DecimalFormat("######0.00");
                for(Item item:totalgoods){
                    Double money =((item.getIsPromotion()==1&&item.getAmount()>1)?(item.getAmount()-item.getAmount()/2):item.getAmount())*item.getPrice()*((item.getIsDiscount()==1&&item.getIsPromotion()!=1)?item.getDiscount():1);
                    totalmoney=totalmoney+money;
                    jtf3.append("名称："+item.getName()+","+"数量："+item.getAmount()+"个"+","+"单价："+item.getPrice()+"（元）"+","+"小计："+df.format(money)+"（元）"+"\n");
                }
                jtf3.append("----------------------"+"\n");
                int check2=0;
                for(Item item:totalgoods){
                    if(item.getAmount()>1&&item.getIsPromotion()==1){
                        check2++;
                    }
                }
                if(check2!=0){
                    jtf3.append("挥泪赠送商品："+"\n");
                    for(Item item:totalgoods){
                        if(item.getAmount()>1&&item.getIsPromotion()==1){
                            jtf3.append("名称："+item.getName()+","+"数量："+item.getAmount()/2+"\n");
                        }
                    }
                    jtf3.append("----------------------"+"\n");
                }
                jtf3.append("总计："+df.format(totalmoney)+"（元）"+"\n");
                int check3=0;
                for(Item item:totalgoods){
                    if((item.getIsDiscount()==1&&item.getDiscount()!=1)||(item.getIsPromotion()==1&&item.getAmount()>1)){
                        realtotalmoney=realtotalmoney+item.getAmount()*item.getPrice();
                        check3++;
                    }
                    else{
                        realtotalmoney=realtotalmoney+item.getAmount()*item.getPrice();
                    }
                }
                if(check3!=0){
                    jtf3.append("节省："+df.format(realtotalmoney-totalmoney)+"（元）"+"\n");
                }
                jtf3.append("**********************"+"\n");
                System.out.println(totalgoods.size());
            }
        });
        //清空购物车
        clearcart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        });
        //添加商品至购物车
        addtocart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(goodindex1.getSelectedRow()!=-1) {
                    System.out.println(goodindex1.getSelectedRow());
                    String barcode = goodindex1.getValueAt(goodindex1.getSelectedRow(), 0).toString();
                    String name = goodindex1.getValueAt(goodindex1.getSelectedRow(), 1).toString();
                    String[] rowValues = {
                            barcode, name
                    };
                    tableModel.addRow(rowValues);
                }
                else{
                    JOptionPane.showMessageDialog(null, "您还没有选择商品", "错误代号3", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //定义方法,添加菜单条和其他控件
    void setMyMenuBar() {
//定义菜单
        jm1 = new JMenu("文件");
        jm2 = new JMenu("编辑");
        jm3 = new JMenu("帮助");
//添加到菜单条
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
//添加菜单项
        jm1.add(jmi1 = new JMenuItem("打开"));
        jm1.add(jmi2 = new JMenuItem("退出"));
        jm2.add(jmi3 = new JMenuItem("复制"));
        jm3.add(jmi4 = new JMenuItem("关于"));
//菜单条加到窗口上
        jframe.setJMenuBar(jmb);
        jframe.setVisible(true);
        jframe.setResizable(false);
    }

     public void setgoodindex(){
        int i=0;
        for(Item item:items){
            String[] rowValues = {
                    item.getBarcode(),item.getName(),item.getUnit(),item.getPrice().toString()
            };
            tableModel2.addRow(rowValues);
        }

     }
    public void printlog(String log){
        jtf2.append(log+"\n");
    }
    public void startMysqlConnection(){
        mysql = new MySQLConnect(this);
        items = mysql.findAllgoods();
    }
}