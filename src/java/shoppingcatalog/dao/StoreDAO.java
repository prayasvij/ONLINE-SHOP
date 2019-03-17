package shoppingcatalog.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shoppingcatalog.dbutil.DBConnection;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;
import shoppingcatalog.dto.OrderDTO;
import shoppingcatalog.dto.viewOrderDTO;

/**
 *
 * @author Vijayvargiya
 */
public class StoreDAO {

    private static PreparedStatement ps1, ps2, ps3, ps4, ps5,ps6,ps7;
    private static Statement s1;

    static {
        try {

            s1 = DBConnection.getConnection().createStatement();
            ps1 = DBConnection.getConnection().prepareStatement("select id,item_name from store_item where item_type= ? ");
            ps2 = DBConnection.getConnection().prepareStatement("select * from store_item where id=? ");
            ps3 = DBConnection.getConnection().prepareStatement("insert into order_master values(?,?,?,?)");
            
            ps4 = DBConnection.getConnection().prepareStatement("insert into order_details values(?,?,?)");
            
            ps5 = DBConnection.getConnection().prepareStatement("Select count(*)as count from order_master");
            ps6=DBConnection.getConnection().prepareStatement("Select * from order_master where cust_name=?");
            ps7=DBConnection.getConnection().prepareStatement("Select * from order_details where order_id=?");
        } catch (SQLException e) {
            System.out.println("exception in prepared statment Store DAo" + e);
        }
    }

    public static List<String> getItemTypes() throws SQLException {
        ArrayList<String> ItemList = new ArrayList<>();
        ResultSet rs = s1.executeQuery("select distinct ITEM_TYPE from store_item");
        while (rs.next()) {
            ItemList.add(rs.getString(1));
        }
        return ItemList;
    }

    public static List<ItemInfoDTO> getItemByType(String itemType) throws SQLException {
        ArrayList<ItemInfoDTO> ItemList = new ArrayList<>();
        ps1.setString(1, itemType);
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            ItemInfoDTO obj = new ItemInfoDTO();
//            System.out.println("rs.getint"+rs.getInt(1)+"rs.getsting"+rs.getString(2));
            obj.setItemId(rs.getInt(1));
            obj.setItemName(rs.getString(2));
            ItemList.add(obj);
        }
        return ItemList;
    }

    public static ItemDTO getItemDetails(int itemId) throws SQLException {
        ItemDTO obj = null;
        ps2.setInt(1, itemId);
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            obj = new ItemDTO();
            obj.setItemId(rs.getInt("id"));
            obj.setItemName(rs.getString("item_name"));
            obj.setItemType(rs.getString("item_type"));
            obj.setItemImage(rs.getString("item_image"));
            obj.setItemDesc(rs.getString("item_des"));
            obj.setItemPrice(rs.getDouble("item_prize"));

        }
        return obj;
    }

    public static boolean addOrder(String custName, ArrayList<ItemDTO> itemList, double totalAmount) throws SQLException {
        ResultSet rs = ps5.executeQuery();
        rs.next();
        int lastId = rs.getInt(1);
        String nextId = "ORD00" + (lastId + 1);
        ps3.setString(1, nextId);
        ps3.setString(2, custName);
        ps3.setDouble(3, totalAmount);
        java.util.Date today = new java.util.Date();//nist server se bhi le sakte hai 
        long ms = today.getTime();
        java.sql.Date currDate = new java.sql.Date(ms);
        ps3.setDate(4, currDate);
        int ans1 = ps3.executeUpdate();
        int count = 0;
        System.out.println("Record inserted in OrderMaster:" + ans1);
        for (ItemDTO item : itemList) {
            ps4.setString(1, nextId);
            ps4.setString(2, item.getItemName());
            ps4.setDouble(3, item.getItemPrice());
            
            int ans2 = ps4.executeUpdate();
            if (ans2 == 1) {
                ++count;
            }
            System.out.println("Record inserted in Order Details:" + ans2);
        }
        return (ans1 == 1 && count == itemList.size());
        //return true;
    }
    
    public static ArrayList<OrderDTO> getOrdersByCustomer(String custName)throws SQLException{
        ArrayList<OrderDTO> orderList=new ArrayList<OrderDTO>();
        ps6.setString(1,custName);  
     ResultSet rs = ps6.executeQuery();
     while(rs.next()){
         OrderDTO obj =new OrderDTO();
         obj.setOrderid(rs.getString(1));
         obj.setOrderAmount(rs.getDouble(3));
         obj.setOrderDate(rs.getDate(4));
         orderList.add(obj);
     }
     return orderList;
    }
    
    public static ArrayList<viewOrderDTO> getOrdersById(String orderId)throws SQLException{
        ArrayList<viewOrderDTO> orderList=new ArrayList<>();
        ps7.setString(1,orderId);  
     ResultSet rs = ps7.executeQuery();
     while(rs.next()){
         viewOrderDTO i= new viewOrderDTO();
         i.setOrderId(orderId);
         i.setItemName(rs.getString(2));
         i.setItemPrice(rs.getDouble(3));
         orderList.add(i);
     }
     return orderList;
    }
    
}
