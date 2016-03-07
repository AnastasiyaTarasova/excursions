/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import logic.*;

/**
 *
 * @author Анастасия
 */

public class ExcursionMapper extends AbstractMapper<Order>{
    public enum ExcursionParams {
	
        name("Name");
		
	private String value;
	
	private ExcursionParams(String value) {
	    this.value = value;
	}
	
	public String getValue() {
	    return value;
	}
    }
   
    public Order findByParam(ExcursionParams param, String value) throws SQLException {
	/*String query = "SELECT * FROM Stores WHERE " + param.getValue() + " = '" + value + "'";
	
	try (Connection conn = getConnection();
		PreparedStatement statement = conn.prepareStatement(query.toString());
		ResultSet rset = statement.executeQuery()) {
	    List<Excursion> stores = getElementsFromResultSet(rset);
	    if (stores != null && !stores.isEmpty()) {
		return stores.get(0);
	    }
	    return null;
	}*/
    return null;
    }
    
    public int insert(Order excursion){
        return 1;}

   /* @Override
      public int insert(Excursion excursion) throws SQLException {
	try (Connection conn = getConnection(); PreparedStatement statement = getInsertStatement(excursion, conn)) {
	    statement.executeUpdate();
	    try (ResultSet keys = statement.getGeneratedKeys()) {
		if (keys == null || !keys.next()) {
		    return -1;
		}
		return keys.getInt(1);
	    }
	}
    }
      
       private PreparedStatement getInsertStatement(Excursion excursion, Connection conn) throws SQLException {
	String query = "INSERT INTO Excursion(Type, Name, LastName, Email, NameOfExcursion, Login, "
		+ "Password) VALUES (?, ?, ?, ?, ?, ?, ?)";
	PreparedStatement statement = null;
	try {
	    statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	    statement.setInt(1, UserTypesEnum.Excursion.getValue());
	    statement.setString(2, excursion.getName());
	    statement.setString(3, excursion.getLastName());
	    statement.setString(4, excursion.getEmail());
            statement.setString(5, excursion.getNameOfExcursion());
	    statement.setString(6, excursion.getLogin());
	    statement.setString(7, excursion.getPassword());
	    return statement;
	} catch (SQLException ex) {
	    statement.close();
	    throw ex;
	}
    }*/

   // @Override
    public void update(Order object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Order object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   // @Override
    public Order find(long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Order> getElementsFromResultSet(ResultSet rset) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

