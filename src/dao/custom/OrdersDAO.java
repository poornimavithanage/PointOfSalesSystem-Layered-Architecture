package dao.custom;

import dao.CRUDDAO;
import dao.SuperDAO;
import entity.Orders;

import java.util.List;

public interface OrdersDAO extends CRUDDAO<Orders,String> {
     String getLastOrderId() throws Exception;


}
