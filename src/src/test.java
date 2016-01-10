import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/5.
 */
public class test {
    @Test
    public void TestGetGoodByBarCode()
    {
        ArrayList<Item> GoodList = new ArrayList<Item>();
        Item g1=new Item();
        g1.setBarcode("1");
        g1.setName("测试1");
        GoodList.add(g1);
        Item g2=new Item();
        g2.setBarcode("2");
        g2.setName("测试2");
        GoodList.add(g2);
        output ct=new output();
        Item g3=ct.getitem("1",GoodList);
        System.out.println(g3.getName());
    }
}
