package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import db.DBConnection;
import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception{

            ResultSet rst = CrudUtil.execute("SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1");
            if (rst.next()){
                return rst.getString(1);
            }else{
                return null;
            }

    }

    @Override
    public List<Customer> findAll() throws Exception{

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
            ArrayList<Customer>customers = new ArrayList<>();
            while (resultSet.next()){
                customers.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
            return customers;

    }

    @Override
    public Customer find(String key) throws Exception{
//        Connection connection = DBConnection.getInstance().getConnection();

//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE customerId=?");
//            pstm.setObject(1,key);
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer WHERE customerId=?");
            if(resultSet.next()){
                return new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            }
            return null;

    }

    @Override
    public boolean save(Customer customer) throws Exception{

//        Connection connection = DBConnection.getInstance().getConnection();
//        Customer customer =  entity;

//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
//            pstm.setObject(1,customer.getCustomerId());
//            pstm.setObject(2,customer.getCustomerName());
//            pstm.setObject(3,customer.getCustomerAddress());
            return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?)",
            customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress());


    }

    @Override
    public boolean update(Customer customer) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            Customer customer =  entity;
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET customerName=?, customerAddress=? WHERE customerId=?");
//            pstm.setObject(1, customer.getCustomerId());
//            pstm.setObject(2, customer.getCustomerName());
//            pstm.setObject(3, customer.getCustomerAddress());

            return CrudUtil.execute("UPDATE Customer SET customerName=?, customerAddress=? WHERE customerId=?"
                    ,customer.getCustomerName(),customer.getCustomerAddress(),customer.getCustomerId());


    }

    @Override
    public boolean delete(String key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE customerId=?");
//            pstm.setObject(1, key);
            return CrudUtil.execute("DELETE FROM Customer WHERE customerId=?");

    }
}
