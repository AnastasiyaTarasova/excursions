package com.excursions.util;

/**
 * Created by root on 15.05.2016.
 */
public enum UserTypes {
    Admin(0),
    Guide(1),
    Client(2);

    private int id;
    public int getId() {
        return id;
    }

    UserTypes(int id) {
        this.id = id;
    }
}
