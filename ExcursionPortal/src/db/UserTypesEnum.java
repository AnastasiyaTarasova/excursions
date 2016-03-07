/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Анастасия
 */

public enum UserTypesEnum {
    Admin(0, "Администратор"), Client(1, "Клиент"), Guide(2, "Экскурсовод");
    
    private int value;
    private String description;

    private UserTypesEnum(int value, String description) {
	this.value = value;
	this.description = description;
    }

    public int getValue() {
	return this.value;
    }

    public String getDescription() {
	return this.description;
    }
    
    @Override
    public String toString() {
	return this.description;
    }
    
}

