package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code hereS
        output a=new output();
        a.Print();
        MyWindow mywin=new MyWindow("我的窗口");
        mywin.setMyMenuBar();
        mywin.startMysqlConnection();
        mywin.setgoodindex();
    }
}
