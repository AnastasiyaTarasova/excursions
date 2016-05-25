package com.excursions.web.models;

import java.io.Serializable;

/**
 * Created by root on 15.05.2016.
 */
public class PostData implements Serializable {
    private String entityType;
    private Object entity;


    public PostData() {
    }


    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    public Object getEntity() {
        return entity;
    }
    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
