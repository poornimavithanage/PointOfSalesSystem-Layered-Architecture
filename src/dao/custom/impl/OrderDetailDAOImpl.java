package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDetailDAO;
import db.DBConnection;
import entity.OrderDetail;
import entity.OrderDetailPK;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetail> findAll() throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            ResultSet rst = CrudUtil.execute("SELECT * FROM OrderDetail");
            List<OrderDetail> orderDetails = new ArrayList<>();
            while (rst.next()) {
                orderDetails.add(new OrderDetail(rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getBigDecimal(4)));
            }
            return orderDetails;

    }

    @Override
    public OrderDetail find(OrderDetailPK key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM OrderDetail WHERE orderId=? AND itemCode=?");
            OrderDetailPK entity = new OrderDetailPK();
//            pstm.setObject(1, entity.getOrderId());
//            pstm.setObject(2, entity.getItemCode());
            ResultSet rst =CrudUtil.execute("SELECT * FROM OrderDetail WHERE orderId=? AND itemCode=?",
                    entity.getOrderId(),entity.getItemCode());
            if (rst.next()) {
                return new OrderDetail(rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getBigDecimal(4));
            }
            return null;

    }

    @Override
    public boolean save(OrderDetail orderDetail) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO OrderDetail VALUES (?,?,?,?)");
//            OrderDetail orderDetail = entity;
//            pstm.setObject(1, orderDetail.getOrderDetailPK().getOrderId());
//            pstm.setObject(2, orderDetail.getOrderDetailPK().getItemCode());
//            pstm.setObject(3, orderDetail.getOrderQty());
//            pstm.setObject(4, orderDetail.getUnitPrice());
            return CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)",orderDetail.getOrderDetailPK().getOrderId(),
                    orderDetail.getOrderDetailPK().getItemCode(),orderDetail.getOrderQty(),orderDetail.getUnitPrice());

    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET orderQty=?, unitPrice=? WHERE orderId=? AND itemCode=?");
//            OrderDetail orderDetail = entity;
//            pstm.setObject(3, orderDetail.getOrderDetailPK().getOrderId());
//            pstm.setObject(4, orderDetail.getOrderDetailPK().getItemCode());
//            pstm.setObject(1, orderDetail.getOrderQty());
//            pstm.setObject(2, orderDetail.getUnitPrice());
            return CrudUtil.execute("UPDATE OrderDetail SET orderQty=?, unitPrice=? WHERE orderId=? AND itemCode=?",
                    orderDetail.getOrderDetailPK(),orderDetail.getOrderDetailPK(),orderDetail.getOrderQty(),orderDetail.getUnitPrice());

    }

    @Override
    public boolean delete(OrderDetailPK key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.
//                    prepareStatement("DELETE FROM OrderDetail WHERE orderId=? AND itemCode=?");
            OrderDetailPK entity = new OrderDetailPK();
//            pstm.setObject(1, entity.getOrderId());
//            pstm.setObject(2, entity.getItemCode());
            return CrudUtil.execute("DELETE FROM OrderDetail WHERE orderId=? AND itemCode=?",entity.getOrderId(),
                    entity.getItemCode());

    }
}
