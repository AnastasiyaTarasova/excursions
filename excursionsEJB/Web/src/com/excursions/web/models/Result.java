package com.excursions.web.models;

import java.io.Serializable;

/**
 * Created by root on 15.05.2016.
 */
public class Result implements Serializable {
    private int id;
    private boolean success;


    public Result() {
    }
    public Result(int id, boolean success) {
        this.id = id;
        this.success = success;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
