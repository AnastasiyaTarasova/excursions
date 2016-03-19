/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author на
 */
public class CurrentUser {
    static public  User currUser;
    public CurrentUser(User usr){currUser = usr;}
    public static User getUser(){return currUser;}
    public static void setUser(User usr){currUser = usr;}
}
