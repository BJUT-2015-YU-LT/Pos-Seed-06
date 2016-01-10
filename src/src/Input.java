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

    public ArrayList<Item> ReturnList()
    {
        String JsonContext = new Input().ReadFile("D:\\小学期\\src\\item.json");
        JSONArray jsonArray = JSONArray.fromObject(JsonContext);
        int size = jsonArray.size();
        Double TotalPrice = 0.0;
        ArrayList<Item> ItemList = new ArrayList<Item>();
       // System.out.println("Size: " + size);
        for(int  i = 0; i < size; i++) {
            Item item = new Item();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
      //      System.out.println("[" + i + "]BarCode=" + jsonObject.get("barcode"));
            item.setBarcode(jsonObject.getString("barcode"));
       //     System.out.println("[" + i + "]Name=" + jsonObject.get("name"));
            item.setName(jsonObject.getString("name"));
        //    System.out.println("[" + i + "]Unit=" + jsonObject.get("unit"));
           item.setUnit(jsonObject.getString("unit"));
       //     System.out.println("[" + i + "]Price=" + jsonObject.get("price"));
           item.setPrice(jsonObject.getDouble("price"));
            if(jsonObject.get("discount") != null)
            {
                item.setDiscount(jsonObject.getDouble("discount"));
            }
            ItemList.add(item);
        }
        return ItemList;
    }

}
