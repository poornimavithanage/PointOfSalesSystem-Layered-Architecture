package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    CustomEntity getOrderDetail(String orderId) throws Exception;

    CustomEntity getOrderDetail2(String orderId) throws Exception;

    CustomEntity searchOrder(String orderId) throws Exception;

}
