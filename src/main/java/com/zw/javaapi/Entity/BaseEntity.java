package com.zw.javaapi.Entity;


import com.sun.istack.internal.NotNull;
import lombok.Data;

@Data
public class BaseEntity {

    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
