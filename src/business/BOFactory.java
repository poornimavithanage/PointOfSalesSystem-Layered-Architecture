package business;

import business.custom.impl.CustomerBOImpl;
import business.custom.impl.ItemBOImpl;
import business.custom.impl.OrdersBOImpl;
import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null) ? boFactory = new BOFactory():boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDERS:
                return (T)new OrdersBOImpl();
            default:
                return  null;
        }
    }

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
