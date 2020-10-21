package dao.custom;

import dao.CRUDDAO;
import dao.SuperDAO;
import entity.Customer;

import java.util.List;

public interface CustomerDAO extends CRUDDAO<Customer,String> {
     String getLastCustomerId() throws Exception;


}
