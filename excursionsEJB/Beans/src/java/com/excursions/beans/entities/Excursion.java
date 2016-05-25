package com.excursions.beans.entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery(name = "excursion-get-all", query = "SELECT e from Excursion as e")
public class Excursion implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int guideId;
    private int price;
    private String name;
    private String description;


    public Excursion() {
    }
    public Excursion(int guideId, int price, String name, String description) {
        this.guideId = guideId;
        this.price = price;
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGuideId() {
        return guideId;
    }
    public void setGuideId(int guideId) {
        this.guideId = guideId;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
