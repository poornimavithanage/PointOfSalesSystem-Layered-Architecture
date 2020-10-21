package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrdersDAO;
import db.DBConnection;
import entity.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {


    @Override
    public String getLastOrderId() throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            ResultSet rst = CrudUtil.execute("SELECT OrderID FROM Orders ORDER BY OrderID DESC LIMIT 1");
            if (rst.next()){
                return rst.getString(1);
            }else{
                return null;
            }

    }

    @Override
    public List<Orders> findAll() throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            ResultSet rst = CrudUtil.execute("SELECT * FROM Orders");
            List<Orders> orders = new ArrayList<>();
            while (rst.next()) {
                orders.add(new Orders(rst.getString(1),
                        rst.getDate(2),
                        rst.getString(3)));
            }
            return orders;

    }

    @Override
    public Orders find(String key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Orders WHERE orderId=?");
//            pstm.setObject(1, key);
            ResultSet rst = CrudUtil.execute("SELECT * FROM Orders WHERE orderId=?");
            if (rst.next()) {
                return new Orders(rst.getString(1),
                        rst.getDate(2),
                        rst.getString(3));
            }
            return null;

    }

    @Override
    public boolean save(Orders entity) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Orders orders =  entity;
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Orders VALUES (?,?,?)");
//            pstm.setObject(1, orders.getOrderID());
//            pstm.setObject(2, orders.getOrderDate());
//            pstm.setObject(3, orders.getCustomerId());
            return CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?)",entity.getOrderID(),
                    entity.getOrderDate(),entity.getCustomerId());

    }

    @Override
    public boolean update(Orders orders) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Orders orders =  entity;
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Orders SET orderDate=?, customerId=? WHERE orderId=?");
//            pstm.setObject(1, orders.getOrderID());
//            pstm.setObject(2, orders.getOrderDate());
//            pstm.setObject(3, orders.getCustomerId());
            return CrudUtil.execute("UPDATE Orders SET orderDate=?, customerId=? WHERE orderId=?",orders.getOrderID(),
                    orders.getOrderDate(),orders.getCustomerId());

    }

    @Override
    public boolean delete(String key)throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Orders WHERE orderId=?");
//            pstm.setObject(1, key);
            return CrudUtil.execute("DELETE FROM Orders WHERE orderId=?");

    }
}
