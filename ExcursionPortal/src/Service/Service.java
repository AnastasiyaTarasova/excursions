/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import db.OrderMapper;
import db.UserMapper;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import logic.Order;
import logic.OrderTransaction;
import logic.User;
import logic.UserTransaction;

/**
 *
 * @author Анастасия
 */
public class Service {

   /**
     * -------------------------Методы, обрабатвающие объекты класса "USER"---------------------------
     */
    public static User login(String login, String password) /*throws SQLException*/ {
	//UserMapper mapper = new UserMapper();
	//User user = mapper.findByParam(login, password);
        User user = null;
        if(login != null && !login.equals("")) 
                user = UserTransaction.login(login, password);
	if (user == null ) {
	    return null;
	}
	return user;
    }
    
    public static User register(Integer type, String name, String lastName, String nameOfExcursion, String city, String email, String login, String password) 
	    throws /*SQLException,*/ IllegalArgumentException {
        
        if(name==null || name.equals("") || lastName==null || lastName.equals("") || city==null || city.equals("") ||
                email==null || email.equals("") || login==null || login.equals("") || password==null || password.equals(""))
        {
            return null;
        }
        else 
            return UserTransaction.register(type, name, lastName, nameOfExcursion, city, email, login, password);
        
    }
    
    /*public boolean equals(Object obj) {
	if (obj instanceof User) {
	    User user = (User) obj;
	    if (user.getId() == this.id && user.getLogin().equals(this.login)
		    && user.getName().equals(this.name) && user.getLastName().equals(this.lastName)
		    && user.getEmail().equals(this.email)) {
		return true;
	    }
	}
	return false;
    }*/
    
    public static int addUser(int type, String name, String lastName, String nameOfExcursion, String city,
                    String email, String login, String password) /*throws SQLException*/ {
        
        UserTransaction user = new UserTransaction();
        int count = 0;
	if(name==null || name.equals("") || lastName==null || lastName.equals("") || 
                nameOfExcursion==null || nameOfExcursion.equals("") || city==null || city.equals("") ||
                email==null || email.equals("") || login==null || login.equals("") || password==null || password.equals(""))
        {
            //Shower.Show( "Введены не все данные");//
            return 0;
        }
        else {
            count = user.addUser(type, name, lastName, nameOfExcursion, city, email, login, password);
            /*if (count == 1) //Shower.Show( "Пользователь с таким именем существует");
            {
                return -1;
            }*/
            
        }
        return count;
   }
    
    public static void removeUser(Long userId) /*throws SQLException*/ {
        //System.out.println("КЛЮЧ = "+ userId);
        UserTransaction.removeUser(userId);
	//Shower.Show( "Пользователь удалён");
        //List<Order> excursions = null;
        //excursions = findOrder_idPer(userId);
        //for(int i=0;i<excursions.size();i++){
        //    excursions.get(i).setDel(Boolean.parseBoolean("True"));
        //    new OrderMapper().update(excursions.get(i));
        //}
        
       // new UserMapper().delete(userId);
       // Shower.Show( "User removed");
    }
    
    public static void removeUserObject(User user) /*throws SQLException*/ {
	new UserMapper().delete(user);
    }
    
    public static int updateUser(Long id,int type,String name, String lastName, String nameOfExcursion, String city,
                    String email, String login, String password) /*throws SQLException*/ {
	/*UserMapper mapper = new UserMapper();
	User getUser = mapper.findByParam(user.getLogin(), user.getPassword());
        //System.out.println("id выбранной записи"+getUser.getId());
	if (getUser.getId() != user.getId()) {
	    Shower.Show( "User exist");
	}
        else {
            new UserMapper().update(user);
            Shower.Show( "Record updated");
        }*/
        if(name==null || name.equals("") || lastName==null || lastName.equals("") || 
                nameOfExcursion==null || nameOfExcursion.equals("") || city==null || city.equals("") ||
                email==null || email.equals("") || login==null || login.equals("") || password==null || password.equals(""))
           return 0;
        else { 
            int count = UserTransaction.updateUser(id, type, name, lastName, nameOfExcursion, city, email, login, password);
            if (count == 1) return 1;//Пользователь существует
            if (count == -2) return -2;//Shower.Show( "Информация обновлена");//
        }   
        return -3;
        }
    
    public static List<User> findNameOfExcursion(String nameOfExcursion) /*throws SQLException*/ {
        List<User> user = null;
        if (nameOfExcursion != null && !nameOfExcursion.equals(""))
            user = UserTransaction.findNameOfExcursion(nameOfExcursion);
        return user;
        }
    
     public static List<User> findLastNameClient(String LastNameClient) /*throws SQLException*/ { 
        List<User> user = null;
        if (LastNameClient != null && !LastNameClient.equals(""))
            user = UserTransaction.findLastNameClient(LastNameClient);
        return user;
     }
     
    public static User find(long id) {
        return UserTransaction.find(id);
    }
     
    public static  List<User> getAll(int type) /*throws SQLException*/ {
        List<User> user = null;
        if (type > 0) user = UserTransaction.getAll(type);
        return user;
    } 
    
    /**
     * -------------------------Методы класса , обрабатвающие объекты класса "ORDER"----------------------------
     */
    
    public static int addOrder(String nameOfExcursion, String cust,
                    String city, Date date, Time time, int duration, int cost,  String status, boolean del) /*throws SQLException*/ {
    OrderTransaction excursion = new OrderTransaction();
    int count = 0;
    if(city==null || city.equals("") || date==null || date.equals("") || 
                time==null || time.equals("") || duration < 0 || cost < 0 )
        //Shower.Show( "Введены не все данные, либо данные не корректны");
        return 0;
    else { 
        count = excursion.addOrder(nameOfExcursion,cust, city, date, time, duration, cost,  status, del);
        /*if (count == -3) Shower.Show( "Экскурсовод занят, попробуйте выбрать другое время");
        if (count != -3) Shower.Show( "Заказ успешно добавлен");*/
    }
    return count;
    }
    
    public static int updateOrder(long id, String nameOfExcursion, String cust,
                    String city, Date date, Time time, int duration, int cost,  String status, boolean del) /*throws SQLException*/ {
        int count = 0;
        if(city==null || city.equals("") || date==null || date.equals("") || 
                time==null || time.equals("") || duration < 0 || cost < 0 )
        count = 0;
    else {
        count = OrderTransaction.updateOrder(id, nameOfExcursion,cust, city, date, time, duration, cost,  status, del);
//        if (count == -3) Shower.Show( "Экскурсовод занят, попробуйте выбрать другое время");
//        if (count == -2) Shower.Show( "Информация обновлена");
        }
        return count;
    }
    
    public static void updateOrder2(Order excursion1) /*throws SQLException*/ {
        new OrderMapper().update(excursion1);
    
    }
    
    public static int removeOrder(Long excursionId) /*throws SQLException*/ {
	new OrderMapper().delete(excursionId);
        return 0;
        //Shower.Show( "Заказ удалён");
    }
    
    public static void removeOrder(Order excursion) /*throws SQLException*/ {
	new OrderMapper().delete(excursion);
    }
    
    public static  List<Order> getAll(boolean bool) /*throws SQLException*/ { 
     return new OrderMapper().getAllOrders(bool);
   }
        
    public static  List<Order> getAll(String name) /*throws SQLException*/ { 
     return new OrderMapper().getAllOrders(name);
   }
    
    public static Order findOrder(long id) /*throws SQLException*/ {
        Order excursion2 = null;
        excursion2 = new OrderMapper().find(id);
        return excursion2;
    }
    
    //public static List<Order> findOrder_idPer(long id) /*throws SQLException*/ {
    //    return new OrderMapper().findIdGuide(id);
    //}

    
}
