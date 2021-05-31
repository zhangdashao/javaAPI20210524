package com.zw.javaapi.Entity;


import lombok.Data;

import javax.validation.constraints.NotNull;

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
