/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.*;

/**
 *
 * @author Анастасия
 */
public class UserMapper extends AbstractMapper<User>{
  
    public enum UserParams {
	Email, Login;
    }
    
    public UserMapper() {
    }
    
    public User findByParam(String _login, String _password) {
	String query = "SELECT * FROM USERSTABLE WHERE login='{login}' and password = '{password}'";
        query = query.replace("{login}", _login)
                     .replace("{password}", _password);
	List<User> users;
	Connection conn;
        try {
            conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet rset = statement.executeQuery(query);
            users = getElementsFromResultSet(rset);	
        } catch (SQLException ex) {
            users = null;
        }
	    
	if (users == null || users.isEmpty()) {
	    return null;
	}

	User user = users.get(0);
	user.getId();

	return user;
    }
    
    @Override
    protected List<User> getElementsFromResultSet(ResultSet rset) throws SQLException {
	List<User> users = new ArrayList<>();
	while (rset.next()) {
	    int id = rset.getInt("ID");
	    int type = rset.getInt("type");  
	    String firstName = rset.getString("firstName");
	    String lastName = rset.getString("lastName");
            String nameOfExcursion = rset.getString("nameOfExcursion");
            String city = rset.getString("city");
	    String email = rset.getString("email");
	    String login = rset.getString("login");
	    String password = rset.getString("password");

	    User user;
            if (type == UserTypesEnum.Admin.getValue()) {
		user = new Admin(type, firstName, lastName, city, email, login, "");
	    } else if (type == UserTypesEnum.Client.getValue()) {
		user = new Client(type, firstName, lastName, city, email, login, "");
	    } else if (type == UserTypesEnum.Guide.getValue()) {
		user = new Guide(type, firstName, lastName, nameOfExcursion, city, email, login, "");
	    } else {
		continue;
	    }
            user.setId(id); 
	    user.setPassword(password);
	    users.add(user);
	}
	return users;
    }
     
    @Override
    public int insert(User user) {
	int userType = 0;
        String userNameOfExcursion = "";
	if (user instanceof Admin) {
	    userType = UserTypesEnum.Admin.getValue();
	} 
        else if (user instanceof Client) {
	    userType = UserTypesEnum.Client.getValue();
	}
        else if (user instanceof Guide) {
	    userType = UserTypesEnum.Guide.getValue();
            userNameOfExcursion = ((Guide)user).getNameOfExcursion();
	}

	try  {
            Connection conn = getConnection(); PreparedStatement statement = getInsertStatement(user, userType,  userNameOfExcursion, conn);
	    //statement.executeUpdate();
            statement.execute();
	    try (ResultSet keys = statement.getGeneratedKeys()) {
		if (keys == null || !keys.next()) {
		    return -1;
		}
		return keys.getInt(1);
	    }
	} catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
       
    }
    
    private static PreparedStatement getInsertStatement(User user, int type,String userNameOfExcursion, Connection conn) throws SQLException {
	String query = "INSERT INTO USERSTABLE(TYPE, firstName, lastName, nameOfExcursion, city, email, login,"
		+ "Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement statement = null;
	try {
	    statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	    statement.setInt(1, type);
            System.out.println("IDD" + user.getId());
	    statement.setString(2, user.getFirstName());
	    statement.setString(3, user.getLastName());
            if (type == UserTypesEnum.Guide.getValue()){
              statement.setString(4, user.getNameOfExcursion());}
            else statement.setString(4, "");
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getEmail());
	    statement.setString(7, user.getLogin());
	    statement.setString(8, user.getPassword());
	    return statement;
	} catch (SQLException ex) {
	    statement.close();
	    throw ex;
	}
    }

    //@Override
    public void update(User user) {
	try {
            Connection conn = getConnection();
            PreparedStatement statement = getUpdateStatement(user,  conn);
            statement.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private PreparedStatement getUpdateStatement(User user, Connection conn) throws SQLException {
         String query = "UPDATE USERSTABLE SET Type = ?, FirstName = ?, LastName = ?, nameOfExcursion = ?,city = ?, Email = ?, "
		+ "Login = ?, Password = ? WHERE Id = ?";
        UserTypesEnum userType = null;
	PreparedStatement statement = null;
        
        if (user instanceof Admin) userType = UserTypesEnum.Admin;
	else if (user instanceof Client) userType = UserTypesEnum.Client;
        else if (user instanceof Guide) userType = UserTypesEnum.Guide;
	
        try {
	    statement = conn.prepareStatement(query);
            statement.setInt(1, userType.getValue());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getNameOfExcursion());
            statement.setString(5, user.getAddress());
	    statement.setString(6, user.getEmail());
	    statement.setString(7, user.getLogin());
	    statement.setString(8, user.getPassword());
	    statement.setLong(9, user.getId());
            //statement.setLong(9, id);
	    return statement;
        } catch (SQLException ex) {
	    statement.close();
	    throw ex;
	}
    }

    @Override
    public void delete(User user) throws SQLException {
	delete(user.getId());
    }
    
    public void delete(Long userId) {
	String query = "DELETE FROM USERSTABLE WHERE Id = ?";
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
	    statement.setLong(1, userId);
	    statement.executeUpdate();
	}catch (SQLException ex) {
            Logger.getLogger(UserMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Поиск по наименованию экскурсии
    public List<User> find(String nameOfExcursion) {
	String query = "SELECT * FROM USERSTABLE WHERE nameOfExcursion = ?";
	List<User> users;
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
	    statement.setString(1, nameOfExcursion);
	    ResultSet rset = statement.executeQuery();
            users = getElementsFromResultSet(rset);
        }catch (SQLException ex) {
            users = null;
        }
	if (users == null || users.isEmpty()) {
	    return null;
	}
        return users;
    }
    
    //Поиск по фамилии клиента
    public List<User> find2(String LastNameClient) {
        String query = "SELECT * FROM USERSTABLE WHERE lastName = ?";
	List<User> users;
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query); 
	    statement.setString(1, LastNameClient);
	    ResultSet rset = statement.executeQuery();
            users = getElementsFromResultSet(rset);
        }catch (SQLException ex) {
            users = null;
        }
	if (users == null || users.isEmpty()) {
	    return null;
        }
	return users;
    }
     
   // @Override
    public User find(long id) {
	String query = "SELECT * FROM USERSTABLE WHERE Id = ?";
	List<User> users;
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
	    ResultSet rset = statement.executeQuery();
            users = getElementsFromResultSet(rset);
        }catch (SQLException ex) {
            users = null;
        }
	if (users == null || users.isEmpty()) {
	    return null;
	}
        User user = users.get(0);
	return user;
    }
        
    public List<User> getAllUsers(int type) {
	String query = "SELECT * FROM USERSTABLE WHERE type = ?";
	List<User> users;
	try {
            Connection conn = getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
	    statement.setLong(1, type);
	    ResultSet rset = statement.executeQuery();
            users = getElementsFromResultSet(rset);
        }catch (SQLException ex) {
            users = null;
        }
	if (users == null || users.isEmpty()) {
	    return null;
        }
        return users;
    }
}

