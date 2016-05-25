package com.excursions.beans.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "booking-get-all", query = "SELECT b from Booking as b")
public class Booking implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int excursionId;


    public Booking() {
    }
    public Booking(int userId, int excursionId) {
        this.userId = userId;
        this.excursionId = excursionId;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getExcursionId() {
        return excursionId;
    }
    public void setExcursionId(int excursionId) {
        this.excursionId = excursionId;
    }
}
