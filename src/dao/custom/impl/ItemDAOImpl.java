package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import db.DBConnection;
import entity.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public String getLastItemCode() throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            ResultSet rst = CrudUtil.execute("SELECT ItemCode FROM Item ORDER BY ItemCode DESC LIMIT 1");
            if (rst.next()){
                return rst.getString(1);
            }else{
                return null;
            }

    }

    @Override
    public List<Item> findAll() throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
            List<Item> items = new ArrayList<>();
            while (rst.next()){
                items.add(new Item(rst.getString(1),
                        rst.getString(2),
                        rst.getBigDecimal(3),
                        rst.getInt(4)));
            }
            return items;

    }

    @Override
    public Item find(String key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE itemCode=?");
//            pstm.setObject(1, key);
            ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE itemCode=?",key);
            if (rst.next()){
                return new Item(rst.getString(1),
                        rst.getString(2),
                        rst.getBigDecimal(3),
                        rst.getInt(4));
            }
            return null;

    }

    @Override
    public boolean save(Item entity) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Item item =  entity;
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
//            pstm.setObject(1, item.getItemCode());
//            pstm.setObject(2, item.getDescription());
//            pstm.setObject(3, item.getUnitPrice());
//            pstm.setObject(4, item.getQtyOnHand());
            return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)",entity.getItemCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());

    }

    @Override
    public boolean update(Item item) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Item item =  entity;
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE itemCode=?");
//            pstm.setObject(4, item.getItemCode());
//            pstm.setObject(1, item.getDescription());
//            pstm.setObject(2, item.getUnitPrice());
//            pstm.setObject(3, item.getQtyOnHand());
            return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE itemCode=?",
                    item.getDescription(),item.getUnitPrice(),item.getQtyOnHand(),item.getItemCode());

    }

    @Override
    public boolean delete(String key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE itemCode=?");
//            pstm.setObject(1, key);
            return CrudUtil.execute("DELETE FROM Item WHERE itemCode=?");

    }
}
