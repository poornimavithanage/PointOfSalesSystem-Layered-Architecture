package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CustomerDAO;
import entity.Customer;
import util.CustomerTM;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);


    public String getNewCustomerId() throws Exception{
        try {
            String lastCustomerId = customerDAO.getLastCustomerId();
            if (lastCustomerId == null) {
                return "C001";
            } else {
                int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
                maxId = maxId + 1;
                String id = "";
                if (maxId < 10) {
                    id = "C00" + maxId;
                } else if (maxId < 100) {
                    id = "C0" + maxId;
                } else {
                    id = "C" + maxId;
                }
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CustomerTM> getAllCustomers()  throws Exception{
        List<Customer> allCustomers = null;
        try {
            allCustomers = customerDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CustomerTM> customers = new ArrayList<>();
        for (Customer customer: allCustomers) {
            customers.add(new CustomerTM(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerAddress()));
        }
        return customers;
    }

    public boolean saveCustomer(String id, String name, String address) {
        try {
            return customerDAO.save(new Customer(id,name,address));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(String customerId) throws Exception{
        try {
            return customerDAO.delete(customerId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCustomer(String name, String address, String customerId)throws Exception{
        try {
            return customerDAO.update(new Customer(customerId,name, address));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
