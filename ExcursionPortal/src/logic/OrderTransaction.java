/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import db.OrderMapper;
import java.sql.Time;
import java.util.Date;
import java.util.List;
//import javax.swing.JOptionPane;

/**
 *
 * @author Анастасия
 */
public class OrderTransaction {
    
    public static int addOrder(String nameOfExcursion,String client,
                    String city, Date date, Time time, int duration, int cost,  String status, boolean del) {
        OrderMapper order2 = new OrderMapper();
        List<User> users, users_;
        users  = UserTransaction.findNameOfExcursion(nameOfExcursion);
        users_ = UserTransaction.findLastNameClient(client);
        User person = UserTransaction.find(users.get(0).getId());
        User cust = UserTransaction.find(users_.get(0).getId());
        
        Order order = new Order(person, cust, city, date, time, duration, cost, status, del);
        
	List<Order> getOrder = order2.findIdOrder(order.getExcursion().getId());
        int counter = 0;
	
            for(int i=0; i < getOrder.size();i++){
                if (getOrder.get(i).getId() != order.getId())
                    if (((getOrder.get(i).getDate()).equals(order.getDate()))/*&&
                            (getOrder.get(i).getTime().equals(order1.getTime()))*/)
                        counter += 1;
            }
            if (counter != 0){
                return -3;//Экскурсовод занят
            }
            else{
                return order2.insert(order);//Заказ добавлен
            }
    }
    
    public static int updateOrder(long id, String nameOfExcursion, String client,
                    String city, Date date, Time time, int duration, int cost,  String status, boolean del) {
        
        OrderMapper order2 = new OrderMapper();
        List<User> users, users_;
        users  = UserTransaction.findNameOfExcursion(nameOfExcursion);
        users_ = UserTransaction.findLastNameClient(client);
        User person = UserTransaction.find(users.get(0).getId());
        User cust = UserTransaction.find(users_.get(0).getId());
        
        Order order = new Order(id, person, cust, city, date, time, duration, cost, status, del);
	List<Order> getOrder = order2.findIdOrder(order.getExcursion().getId());
        int counter = 0;
       	for(int i=0; i < getOrder.size();i++){
            if (getOrder.get(i).getId() != order.getId())
                if ((getOrder.get(i).getDate()).equals(order.getDate()))
                    counter += 1;
        }
        if (counter != 0) return -3; //Экскурсовод занят
        else {
            new OrderMapper().update(order);
            return -2;//Запись обновлена 
        }
    }

    
    
}


