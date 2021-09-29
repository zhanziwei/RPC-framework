package com.zhanziwei.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

public class HelloRequest implements Serializable {
    private Integer id;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloRequest(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
}
