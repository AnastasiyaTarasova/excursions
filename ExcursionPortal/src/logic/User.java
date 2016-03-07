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
public class User {

    protected long id;
    protected int type;
    protected String firstName;
    protected String lastName;
    protected String nameOfExcursion;
    protected String city;
    protected String email;
    protected String login;
    protected String password;
    
    /**
     * @param id
     * @param type
     * @param firstName
     * @param lastName
     * @param nameOfExcursion
     * @param city
     * @param email
     * @param login
     * @param password
     * @throws IllegalArgumentException если имя или Логин является пустым или 
     * длиной менее 3 символа
     */
    
    public User() {}
    
    public User(int type, String firstName, String lastName, String nameOfExcursion, String city, String email,
            String login, String password) {
        //if (firstName == null) {
        //    throw new IllegalArgumentException("Illegal firstName.");
        //} else 
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameOfExcursion = nameOfExcursion;
        this.city = city;
        this.email = email;
        this.login = login;
        this.password = password;
	}
       
    public User(long id, int type, String firstName, String lastName, String nameOfExcursion, String city, String email,
            String login, String password) 
	    throws IllegalArgumentException {
        //if (firstName == null) {
        //    throw new IllegalArgumentException("Illegal firstName.");
        //} else if (login == null || login.length() < 3) {
        //    throw new IllegalArgumentException("Illegal login.");
        //}      
	this.id = id;
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameOfExcursion = nameOfExcursion;
        this.city = city;
        this.email = email;
        this.login = login;
        this.password = password;
	}
    
    public void setId(int id)                           {this.id = id;}
    public long getId()                                 {return id;}
    public int getType()                                {return type;}
    public void setType(int type)                       {this.type = type;}
    public String getFirstName()                        {return firstName;}
    public void setFirstName(String firstName)          {this.firstName = firstName;}
    public String getLastName()                          {return lastName;}
    public void setLastName(String lastName)              {this.lastName = lastName;}
    public String getNameOfExcursion()                    {return nameOfExcursion;}
    public void setNameOfExcursion(String nameOfExcursion)  {this.nameOfExcursion = nameOfExcursion;}
    public String getAddress()                          {return city;}
    public void setAddress(String city)               {this.city = city;}
    public String getEmail()                            {return email;}
    public void setEmail(String email)                  {this.email = email;}
    public String getLogin()                            {return login;}
    public void setLogin(String login)                  {this.login = login;}
    public String getPassword()                         {return password;}
    public void setPassword(String password)            {this.password = password;}

}