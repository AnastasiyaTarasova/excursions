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
public class Admin extends User {

    public Admin(){}
    
    public Admin(int type, String name, String lastName, String city, String email, 
            String login, String password) throws IllegalArgumentException {
        super(type, name, lastName, "", city, email, login, password);
    }
}

