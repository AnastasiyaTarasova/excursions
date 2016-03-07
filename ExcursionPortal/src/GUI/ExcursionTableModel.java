/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import logic.User;
/**
 *
 * @author Анастасия
 */
public class ExcursionTableModel extends AbstractTableModel{
    
    private String[] columnNames={"Id","Название экскурсии","Имя", "Фамилия", "E-mail"};
    String[][] data = null;
    ArrayList<User> excursions;
        
    public ExcursionTableModel(ArrayList<User> excursions) throws SQLException {
        this.excursions = excursions;
        data = new String[excursions.size()][5];
        if (excursions.size()!= 0)
            for(int i=0; i < excursions.size();i++){
                data[i][0]=Long.toString(excursions.get(i).getId());
                data[i][1]=excursions.get(i).getNameOfExcursion();
                data[i][2]=""+excursions.get(i).getFirstName();
                data[i][3]=""+excursions.get(i).getLastName();
                data[i][4]=""+excursions.get(i).getEmail();
            }
     //fireTableDataChanged();//обновляем данные
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return data[rowIndex][columnIndex];
    }
    
    public void setValueAt(String value, int rowIndex, int columnIndex){
      data[rowIndex][columnIndex] = value;
    }
   
    public void removeRow(int rowIndex){
        for(int i=0; i < rowIndex;i++){
            for(int j=0; j < getColumnCount();j++){
                data[i][j] =data[i][j];
                System.out.println(data[i][j]);
            }          
        }
        for(int i=rowIndex; i < getRowCount()-1;i++){
            for(int j=0; j < getColumnCount();j++){
                data[i][j] =data[i+1][j];
            }
        }
        fireTableDataChanged();//обновляем данные 
    }
        
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
}
