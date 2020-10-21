package dao;

import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.OrdersDAO;
import dao.custom.impl.*;

public class DAOFactory {
   private static DAOFactory daoFactory;

   private DAOFactory(){

   }

  public static DAOFactory getInstance(){
       return (daoFactory == null) ? daoFactory = new DAOFactory():daoFactory;
   }

   public <T extends SuperDAO> T getDAO(DAOType daoType){
       switch (daoType){
           case CUSTOMER:
               return (T) new CustomerDAOImpl();
           case ITEM:
               return (T) new ItemDAOImpl();
           case ORDERS:
               return (T)new OrdersDAOImpl();
           case ORDER_DETAIL:
               return (T)new OrderDetailDAOImpl();
           case QUERY:
               return (T) new QueryDAOImpl();
           default:
               return  null;
       }
   }
/*
   public CustomerDAO getCustomerDAO(){
       return new CustomerDAOImpl();
   }

   public ItemDAO getItemDAO(){
       return new ItemDAOImpl();
   }

   public OrdersDAO getOrderDAO(){
       return new OrdersDAOImpl();
   }

   public OrderDetailDAO getOrderDetailDAO(){
       return new OrderDetailDAOImpl();
   }
*/

    public SuperDAO getDAO(int daoType){
        switch (daoType){
            case 0:
                return new CustomerDAOImpl();
            case 1:
                return new ItemDAOImpl();
            case 2:
                return new OrdersDAOImpl();
            case 3:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
