/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.sql.Time;
import java.util.Date;
/**
 *
 * @author Анастасия
 */
public class Order {
    protected long id;
    protected User Excursion;
    protected User Client;
    protected String city;
    protected Date date;
    protected Time time;
    protected int duration;
    protected int cost;
    protected String status;
    protected boolean del;
    
    
    
    public Order (){}
    
    public Order(long id, User Excursion, User Client, String address, /*java.sql.Date date, Time time*/Date date, Time time, int duration, int sum, String status, boolean del) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    this.id = id;
    this.Excursion = Excursion;
    this.Client = Client;
    this.city = address;
    this.date = date;
    this.time = time;
    this.duration = duration;
    this.cost = sum;
    this.status = status;
    this.del = del;
    }
    
    public Order(User Excursion, User Client, String address, /*java.sql.Date date, Time time*/Date date, Time time, int duration, int sum, String status, boolean del) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    this.id = id;
    this.Excursion = Excursion;
    this.Client = Client;
    this.city = address;
    this.date = date;
    this.time = time;
    this.duration = duration;
    this.cost = sum;
    this.status = status;
    this.del = del;
    
    }
    
    public void setId(int id)                           {this.id = id;}
    public long getId()                                 {return id;}
    public User getExcursion()                          {return Excursion;}
    public User getClient()                           {return Client;}
    public void setExcursion(Guide excursion)       {this.Excursion = excursion;}
    public void setClient(Client client)          {this.Client = client;}
    public String getAddress()                          {return city;}
    public void setAddress(String address)              {this.city = address;}
    public Date getDate()                               {return date;}
    public void setDate(Date date)                      {this.date = date;}
    public Time getTime()                               {return time;}
    void setTime(Time time)                             {this.time = time;}
    public int getMinut()                               {return duration;}
    public void setMinut(int duration)                     {this.duration = duration;}
    public int getSum()                                 {return cost;}
    public void setSum(int sum)                         {this.cost = sum;}
    public String getStatus()                           {return status;}
    public void setStatus(String status)                {this.status = status;}
    public void setDel(boolean del)                     {this.del = del;}
    public boolean getDel()                              {return del;}

    
    public String[] GetAvailableStatus(/*String str*/){
        String []result = new String[2];
        if(CurrentUser.getUser() instanceof Client){
            result[0] = "На рассмотрении";
            return result;
        }
        switch (status)
        {
            case "На рассмотрении":
                result[0] = "Одобрена"; 
                result[1]="Отказано";
                break;
            case "Одобрена":
                result[0] = "Проведена";
                break;
            default:
                result[0] = "Одобрена";
                
        }
         return result;  
    }
}

