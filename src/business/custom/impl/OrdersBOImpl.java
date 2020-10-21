package business.custom.impl;

import business.custom.OrdersBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.OrdersDAO;
import dao.custom.impl.ItemDAOImpl;
import db.DBConnection;
import entity.Item;
import entity.OrderDetail;
import entity.Orders;
import util.OrderDetailTM;
import util.OrderTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class OrdersBOImpl implements OrdersBO {
    private OrdersDAO ordersDAO =  DAOFactory.getInstance().getDAO(DAOType.ORDERS);
    private OrderDetailDAO orderDetailDAO =  DAOFactory.getInstance().getDAO(DAOType.ORDER_DETAIL);
    private ItemDAO itemDAO =  DAOFactory.getInstance().getDAO(DAOType.ITEM);

    public String getNewOrderId() throws Exception {
        try {
            String lastOrderId = null;
            try {
                lastOrderId = ordersDAO.getLastOrderId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (lastOrderId == null){
                return "D001";
            }else{
                int maxId=  Integer.parseInt(lastOrderId.replace("D",""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "D00" + maxId;
                } else if (maxId < 100) {
                    id = "D0" + maxId;
                } else {
                    id = "D" + maxId;
                }
                return id;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails)throws Exception{
//        System.out.println(order.toString());
//        System.out.println(orderDetails);
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = ordersDAO.save(new Orders(order.getOrderId(),
                    Date.valueOf(order.getOrderDate()),order.getCustomerId()));
            if (!result){
                connection.rollback();
                return false;
            }
            for (OrderDetailTM orderDetail: orderDetails) {
                result = orderDetailDAO.save(new OrderDetail(order.getOrderId(),orderDetail.getCode(),
                        orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())));
                if(!result){
                    connection.rollback();
                    return false;
                }
                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                result = new ItemDAOImpl().update(item);
                if(!result){
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;

        } catch (Throwable throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
