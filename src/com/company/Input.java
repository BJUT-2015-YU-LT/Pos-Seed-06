package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Created by Administrator on 2016/1/5.
 */
public class Input {
    public String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    public ArrayList<Item> ReturnIndexList()
    {
        String JsonContext = new Input().ReadFile("C:\\Users\\Y50\\IdeaProjects\\小学期\\src\\itemwithindex.json");
        JSONObject jsonObject = JSONObject.fromObject(JsonContext);
        int size = jsonObject.size();
        Double TotalPrice = 0.0;
        Iterator it = jsonObject.keys();
        ArrayList<Item> ItemList = new ArrayList<Item>();

        while(it.hasNext()){
            Item item = new Item();
            String barcode=it.next().toString();
            JSONObject childjsonObject = jsonObject.getJSONObject(barcode);

            //item.setKey(it.next().toString());
           // System.out.println("Key=" + it.next().toString());
            item.setBarcode(barcode);
            System.out.println("BarCode=" + barcode);

            item.setName(childjsonObject.getString("name"));
            System.out.println("Name=" + childjsonObject.get("name"));

            item.setUnit(childjsonObject.getString("unit"));
            System.out.println("Unit=" + childjsonObject.get("unit"));

            item.setPrice(childjsonObject.getDouble("price"));
            System.out.println("Price=" + childjsonObject.get("price"));

            if(childjsonObject.get("discount") != null)
            {
                item.setDiscount(childjsonObject.getDouble("discount"));
            }
            ItemList.add(item);
        }
        return ItemList;
        // System.out.println("Size: " + size);
      /*  for(int  i = 0; i < size; i++) {
            Item item = new Item();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
      //      System.out.println("[" + i + "]BarCode=" + jsonObject.get("barcode"));
            item.setBarcode(jsonObject.getString("barcode"));
       //     System.out.println("[" + i + "]Name=" + jsonObject.get("name"));
            item.setName(jsonObject.getString("name"));
        //    System.out.println("[" + i + "]Unit=" + jsonObject.get("unit"));
           item.setUnit(jsonObject.getString("unit"));
       //     System.out.println("[" + i + "]Price=" + jsonObject.get("price"));
           item.setPrice(jsonObject.getDouble("price"));;
            if(jsonObject.get("discount") != null)
            {
                item.setDiscount(jsonObject.getDouble("discount"));
            }
            ItemList.add(item);
        }
        return ItemList;*/
    }

    public ArrayList<Item> ReturnList()
    {
        String JsonContext = new Input().ReadFile("C:\\Users\\Y50\\IdeaProjects\\小学期\\src\\itemlist.json");
        JSONArray jsonArray = JSONArray.fromObject(JsonContext);
        int size = jsonArray.size();
        Double TotalPrice = 0.0;
        ArrayList<Item> ItemList = new ArrayList<Item>();

        for(int  i = 0; i < size; i++){
            Item item = new Item();
            Object obj = jsonArray.get(i);
            String barcode=obj.toString();



            item.setBarcode(barcode);
            System.out.println("BarCode=" + barcode);


            ItemList.add(item);
        }
        return ItemList;
    }

}
