/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import Service.Service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.*;

/**
 *
 * @author Анастасия
 */
public class OrderMapper extends AbstractMapper<Order> {
    
    SimpleDateFormat formDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formTime = new SimpleDateFormat("hh:mm a");
    
    @Override
    public int insert(Order order) {
        System.out.println("order.getDate 3"+order.getDate());
        System.out.println("order.getId3 "+order.getId());
        System.out.println("order.idtClient3 "+order.getClient().getId());
	try (Connection conn = getConnection(); PreparedStatement statement = getInsertStatement(order, conn)) {
            System.out.println("Проверка:   ");
            statement.executeUpdate();
	    try (ResultSet keys = statement.getGeneratedKeys()) {
		if (keys == null || !keys.next()) {
		    return -1;
		}
		return keys.getInt(1);
	    }
	}catch (SQLException ex) {
            return -1;
        }
    }
    private PreparedStatement getInsertStatement(Order order, Connection conn) throws SQLException {
        System.out.println("order.getDate 4"+order.getDate());
        System.out.println("order.getId4 "+order.getId());
        System.out.println("order.idtClient4 "+order.getClient().getId());
	String query = "INSERT INTO ORDERSTABLE(idExcursion, idClient, adress, duration, cost, status, DATE_, TIME_, DEL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        System.out.println("order.getDate 5"+order.getDate());
        System.out.println("order.getId5 "+order.getId());
        System.out.println("order.idtClient 5 "+order.getClient().getId());
	PreparedStatement statement = null;
	try {
	    statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //System.out.println("ID:   "+ order.getId());
	    //statement.setLong(1, order.getId());
            statement.setObject(1, order.getExcursion().getId()); System.out.println("order.Excursion "+order.getExcursion().getId());
            statement.setObject(2, order.getClient().getId());System.out.println("order.Client "+order.getClient().getId());
            statement.setString(3, order.getAddress());System.out.println("order.Address "+order.getAddress());
            statement.setInt(4, order.getMinut());System.out.println("order.getMinut "+order.getMinut());
            statement.setInt(5, order.getSum());System.out.println("order.getSum "+order.getSum());
            statement.setString(6, order.getStatus());System.out.println("order.Status "+order.getStatus());
            statement.setString(7, formDate.format(order.getDate()));System.out.println("order.getDate "+order.getDate());
            statement.setString(8, formTime.format(order.getTime()));System.out.println("order.getTime "+order.getTime());
            statement.setBoolean(9, order.getDel());System.out.println("order.getTime "+order.getDel());
            
            return statement;
	} catch (SQLException ex) {
	    statement.close();
	    throw ex;
	}
    }

    public void delete(Long orderId)/*throws SQLException*/ {
       try{
            String query = "DELETE FROM ORDERSTABLE WHERE id = ?";
            try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setLong(1, orderId);
                statement.executeUpdate();
            }
	}catch (SQLException ex){}
    }
    
    @Override
    public void delete(Order order) /*throws SQLException*/ {
	
        try{
           delete(order.getId());
        } catch (Exception ex){}
    }
    
    //@Override
    public void update(Order order) {
	try {
            Connection conn = getConnection();
            PreparedStatement statement = getUpdateStatement(order, conn);
	    statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PreparedStatement getUpdateStatement(Order order, Connection conn) throws SQLException {
	String query = "UPDATE ORDERSTABLE SET idExcursion = ?, idClient = ?, adress = ?, duration = ?, cost = ?, status = ?, date_ = ?, time_ = ?, del = ? WHERE id = ?";
	PreparedStatement statement = null;
       	try {
	    statement = conn.prepareStatement(query);
	    statement.setObject(1, order.getExcursion().getId());
            statement.setObject(2, order.getClient().getId());
	    statement.setString(3, order.getAddress());
            //ResultSet statement.getResultSet();
            //System.out.println("order.getAddress() "+  statement.getResultSet().getString("city"));
	    statement.setLong(4, order.getMinut());
	    statement.setLong(5, order.getSum());
	    statement.setString(6, order.getStatus());
            statement.setString(7, formDate.format(order.getDate()));
	    statement.setString(8, formTime.format(order.getTime()));
            statement.setBoolean(9, order.getDel());
            statement.setLong(10, order.getId());
            return statement;
	} catch (SQLException ex) {
	    statement.close();
	    throw ex;
	}
    }
   
    @Override
    protected List<Order> getElementsFromResultSet(ResultSet rset) throws SQLException {
	List<Order> orders = new ArrayList<>();
	while (rset.next()) {
	    long id = rset.getLong("id");
	    User _excursion = Service.find(rset.getLong("idExcursion"));
            User _customer = Service.find(rset.getLong("idClient"));
	    String address = rset.getString("adress");
	    Date date = rset.getDate("date_");
            Time time = rset.getTime("time_");
	    int duration = rset.getInt("duration");
            int sum = rset.getInt("cost");
	    String status = rset.getString("status");
            boolean del = rset.getBoolean("del");

	    Order order;
	    order = new Order(id, _excursion, _customer, address, date, time, duration, sum, status, del);
              System.out.println("ПРОВЕРКА: "+order.getStatus());
	    orders.add(order);
	}
	return orders;
    }

    public List<Order> getAllOrders() throws SQLException {
        String buff = "";
        if(CurrentUser.getUser() instanceof Client)
            buff = " WHERE IDCLIENT = " + CurrentUser.getUser().getId();
        if(CurrentUser.getUser() instanceof Guide)
            buff = " WHERE IDEXCURSION = " + CurrentUser.getUser().getId();
	String query = "SELECT * FROM ORDERSTABLE" + buff;
	List<Order> orders;
	try (Connection conn = getConnection();
		Statement statement = conn.createStatement();
		ResultSet rset = statement.executeQuery(query)) {
            orders = getElementsFromResultSet(rset);
        }
	if (orders == null || orders.isEmpty()) {
	    return null;
	}
        return orders;
    }
    
    public List<Order> getAllOrders(boolean bool) /*throws SQLException*/ {
        String buff = "";
        if(CurrentUser.getUser() instanceof Client)
            buff = " AND IDCLIENT = " + CurrentUser.getUser().getId();
        if(CurrentUser.getUser() instanceof Guide)
            buff = " AND IDEXCURSION = " + CurrentUser.getUser().getId();
	String query = "SELECT * FROM ORDERSTABLE WHERE DEL = ?" + buff;
	List<Order> orders = null;
	try {
            Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query);
	    statement.setBoolean(1, bool);
	    try (ResultSet rset = statement.executeQuery()) {
		orders = getElementsFromResultSet(rset);
	    }
	}
        catch (SQLException e){}
	if (orders == null || orders.isEmpty()) {
	    return null;
	}
        return orders;
    }
    
    public List<Order> getAllOrders(String _name) /*throws SQLException*/ {
        String buff = "";
        if(CurrentUser.getUser() instanceof Client)
            buff = " AND IDCLIENT = " + CurrentUser.getUser().getId();
        if(CurrentUser.getUser() instanceof Guide)
            buff = " AND IDEXCURSION = " + CurrentUser.getUser().getId();
	String query = "SELECT * FROM ORDERSTABLE WHERE idExcursion = ?" + buff;
	List<Order> orders = null;
        List<User> users;
        users = Service.findNameOfExcursion(_name);
	try  {
            Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query);
	    statement.setLong(1, users.get(0).getId());
	    try (ResultSet rset = statement.executeQuery()) {
		orders = getElementsFromResultSet(rset);
	    }
	}
        catch (Exception e){}
	return orders;
    }
     
    // @Override
    public Order find(long id) /*throws SQLException*/ {
	String query = "SELECT * FROM ORDERSTABLE WHERE Id = ?";
	List<Order> orders = null;
	try  {Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(query);
	    statement.setLong(1, id);
	    try (ResultSet rset = statement.executeQuery()) {
		orders = getElementsFromResultSet(rset);
	    }
	}
        catch (SQLException e){}
	if (orders == null || orders.isEmpty()) {
	    return null;
	}
        Order order = orders.get(0);
	return order;
    }
    
    //Поиск по idExcursion в таблице ORDERSTABLE
    public List<Order> findIdOrder(long id) {
        String query = "SELECT * FROM ORDERSTABLE WHERE idExcursion = ?";
	List<Order> orders;
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
	    statement.setLong(1, id);
            ResultSet rset = statement.executeQuery(); 
            orders = getElementsFromResultSet(rset);
        }catch (SQLException ex) {
            return null;
        }
        return orders;
    }
    
    //выставить заявку в состояние Принята/Выполнено.
    public void OrderAccepted(User user){
        
        Order order = null;
        
        if (user instanceof Admin) {
	    //Если заказ на рассмотрении, мы можем перевести его в статус Принят.
            if(order.getStatus().equals("На рассмотрении")){
                order.setStatus("Принят");
            }
        } 
        //Если заказ принят, мы можем перевести его в статус Выполнен.
        else if (user instanceof Guide) {
            if (order.getStatus().equals("Принят")){
                order.setStatus("Выполнено");
            }
        }
    }
   
}

