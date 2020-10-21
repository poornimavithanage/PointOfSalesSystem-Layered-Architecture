package dao.custom;

import dao.CRUDDAO;
import dao.SuperDAO;
import entity.Item;

import java.util.List;

public interface ItemDAO extends CRUDDAO<Item,String> {

     String getLastItemCode() throws Exception;
}
