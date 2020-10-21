package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public CustomEntity getOrderDetail(String orderId) throws Exception {
       // Connection connection = DBConnection.getInstance().getConnection();

           /* PreparedStatement pstm = connection.prepareStatement("SELECT o.orderId,c.customerName,o.orderDate " +
                    "FROM orders INNER JOIN orders o ON o.CustomerID = o.CustomerID \n" +
                    "    INNER JOIN customer c on orders.CustomerID = c.CustomerID WHERE o.OrderID=?");
            pstm.setObject(1, orderId);*/
            ResultSet rst = CrudUtil.execute("SELECT o.orderId,c.customerName,o.orderDate " +
                    "FROM orders INNER JOIN orders o ON o.CustomerID = o.CustomerID \n" +
                    "    INNER JOIN customer c on orders.CustomerID = c.CustomerID WHERE o.OrderID=?",orderId);
            if (rst.next()) {
                return new CustomEntity(rst.getString(1),
                        rst.getString(2),
                        rst.getDate(3));
            }
            return null;

    }

    @Override
    public CustomEntity getOrderDetail2(String orderId) throws Exception{
//        Connection connection = DBConnection.getInstance().getConnection();

//            PreparedStatement pstm = connection.prepareStatement("SELECT c.customerId,c.customerName,o.orderId FROM orders o " +
//                    "INNER JOIN customer c ON o.CustomerID = c.CustomerID WHERE OrderID=?");
//            pstm.setObject(1, orderId);
            ResultSet rst = CrudUtil.execute("SELECT c.customerId,c.customerName,o.orderId FROM orders o \" +\n" +
                    "                    \"INNER JOIN customer c ON o.CustomerID = c.CustomerID WHERE OrderID=?",orderId);
            if (rst.next()) {
                return new CustomEntity(rst.getString(1),
                        rst.getString(2),
                        rst.getString(3));
            }
            return null;



    }

    @Override
    public CustomEntity searchOrder(String orderId) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            List<CustomEntity>orders = new ArrayList<>();
//            PreparedStatement pstm = connection.prepareStatement("SELECT o.OrderID,c.customerName,o.OrderDate,o.customerID,SUM(i.UnitPrice * od.OrderQTY)AS Total FROM\n" +
//                    "    (((orders o INNER JOIN orderdetail od ON o.OrderID = od.OrderID)INNER JOIN customer c ON c.CustomerID=o.CustomerID)\n" +
//                    "        INNER JOIN item i ON i.ItemCode=od.ItemCode)GROUP BY o.OrderID");
            ResultSet rst = CrudUtil.execute("SELECT o.OrderID,c.customerName,o.OrderDate,o.customerID,SUM(i.UnitPrice * od.OrderQTY)AS Total FROM\n" +
                    "    (((orders o INNER JOIN orderdetail od ON o.OrderID = od.OrderID)INNER JOIN customer c ON c.CustomerID=o.CustomerID)\n" +
                    "        INNER JOIN item i ON i.ItemCode=od.ItemCode)WHERE o.OrderID=?",orderId);
            if (rst.next()){
                return new  CustomEntity(rst.getString(1),
                        rst.getString(2),
                        rst.getDate(3),
                        rst.getString(4),
                        rst.getBigDecimal(5));
            }


        return null;

    }



}
