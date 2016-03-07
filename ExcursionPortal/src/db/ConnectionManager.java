/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Анастасия
 */

public class ConnectionManager {
    private static ConnectionManager instance;
    Statement st;
    ResultSet rs;
    
    ConnectionManager() {
     };
     
    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    
    Connection conn = null;
    
    public Connection getConnection(){
        boolean result;
        //подключение драйвера
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //параметры подключения
            String url = "jdbc:derby://localhost:1527/Userbase";
            String name = "anastasiya";
            String password = "";

            //установка соединения с БД
            try {
                conn = DriverManager.getConnection(url);
                //System.out.println("есть подключение к БД");
                result = true;
            } catch (java.sql.SQLException e) {
                //System.err.println("ошибка подключения к БД");
                result = false;
            }

            //проверка на наличие данных в БД
            if (conn == null) {
             System.err.println("БД  не подключена.");
            } else {
             System.out.println("БД  подключена.");
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("Драйвер не подключен");
            result = false;
        }
        return conn;
    }
}


