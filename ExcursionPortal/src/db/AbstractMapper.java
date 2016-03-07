/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Анастасия
 */
public abstract class AbstractMapper<T> {

    public AbstractMapper() {
    }

    public abstract int insert(T object) throws SQLException;
    //public abstract void update(T object) throws SQLException;
    public abstract void delete(T object) throws SQLException;
    //public abstract T find(long id) throws SQLException;
    protected abstract List<T> getElementsFromResultSet(ResultSet rset) throws SQLException;
    
    protected Connection getConnection() throws SQLException {
	return new ConnectionManager().getConnection();
    }
}

