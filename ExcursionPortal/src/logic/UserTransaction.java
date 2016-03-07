/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import Service.Service;
import db.OrderMapper;
import db.UserMapper;
import java.util.List;


/**
 *
 * @author Анастасия
 */
public class UserTransaction {
    
    public static User login(String login, String password)  {
	UserMapper mapper = new UserMapper();
	User user = mapper.findByParam(login, password);
	if (user == null ) {
	    return null;
	}
	return user;
    }
    
    public static User register(Integer type, String firstName, String lastName, String nameOfExcursion, String city, String email, String login, String password) 
	    throws IllegalArgumentException {
        //System.out.println("Type2 = "+ type);
	UserMapper mapper = new UserMapper();
	if (mapper.findByParam(login, password) != null) {
	    return null;
            }
        //System.out.println("Type3 = "+ type);
        if (type == 0) mapper.insert(new Admin(type, firstName, lastName, city, email, login, password));
        if (type == 1) mapper.insert(new Client(type, firstName, lastName, city, email, login, password));
        if (type == 2) mapper.insert(new Guide(type, firstName, lastName,nameOfExcursion, city, email, login, password));
       
	return mapper.findByParam(login, password);
    }
    
    public static int addUser(int type, String firstName, String lastName, String nameOfExcursion, String city,
                    String email, String login, String password)  {
        UserMapper mapper = new UserMapper();
        User user = new Guide(2, firstName, lastName, nameOfExcursion, city, email, login, password);
	User getUser = mapper.findByParam(user.getLogin(), user.getPassword());
	if (getUser != null) {
	    return 1;//Пользователь существует
	}
        else 
            return mapper.insert(user);
    }
    
    public static void removeUser(Long userId)throws IllegalArgumentException  {
	
        List<Order> orders = null;
        orders = new OrderMapper().findIdOrder(userId);
        for(int i=0;i<orders.size();i++){
            orders.get(i).setDel(Boolean.parseBoolean("True"));
            new OrderMapper().update(orders.get(i));
        }
        
        new UserMapper().delete(userId);
        
    }
    
    public static int updateUser(long id, int type, String firstName, String lastName, String nameOfExcursion, String city,
                    String email, String login, String password)throws IllegalArgumentException {
	UserMapper mapper = new UserMapper();
        User user = new Guide(id,2, firstName, lastName, nameOfExcursion, city, email, login, password);
	User getUser = mapper.findByParam(user.getLogin(), user.getPassword());
        //System.out.println("id выбранной записи"+getUser.getId());
	if (getUser.getId() != user.getId()) {
	    return 1;//Пользователь существует
	}
        else {
            new UserMapper().update(user);
            return -2; //Запись обновлена
        }
        //return 0;
        }
     
    public static List<User> findNameOfExcursion(String nameOfExcursion) throws IllegalArgumentException {
        UserMapper user = new UserMapper();
        List<User> listUser = user.find(nameOfExcursion);
        if (listUser != null && listUser.size()!=0){
            return listUser;
        }
        else{
            listUser = null;
            return   listUser;
        }
    }
    
    public static List<User> findLastNameClient(String LastNameClient)throws IllegalArgumentException {
       UserMapper user = new UserMapper();
        List<User> listUser = user.find2(LastNameClient);
        if (listUser != null && listUser.size()!=0){
            return listUser;
        }
        else
            return   listUser = null; 
     
    }
    
    public static User find(long id)throws IllegalArgumentException {
        User user2 = null;
        user2 = new UserMapper().find(id);
        return user2;
    }
    
    public static  List<User> getAll(int type)throws IllegalArgumentException { 
        return new UserMapper().getAllUsers(type);
    }
    
}
