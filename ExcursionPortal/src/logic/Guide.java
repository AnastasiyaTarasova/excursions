/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Анастасия
 */
public class Guide extends User {
    
    private String nameOfExcursion;

    public Guide(){}
            
    public Guide(int type, String name, String lastName, String nameOfExcursion, String city, String email,
             String login, String password) 
            throws IllegalArgumentException {
        super(type, name, lastName, nameOfExcursion, city, email, login, password);
      
        this.nameOfExcursion = nameOfExcursion;
    }
   
    public Guide(long id, int type, String name, String lastName, String nameOfExcursion, String city, String email,
             String login, String password) throws IllegalArgumentException {
        super(id, type, name, lastName, nameOfExcursion, city, email, login, password);
        
        this.nameOfExcursion = nameOfExcursion;
    }
       
    public String getNameOfExcursion()                    {return this.nameOfExcursion;}
    public void setNameOfExcursion(String nameOfExcursion)  {this.nameOfExcursion = nameOfExcursion;}

}

