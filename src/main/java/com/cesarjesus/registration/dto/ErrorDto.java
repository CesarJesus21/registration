package com.cesarjesus.registration.dto;


import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
public class ErrorDto implements Serializable {
    private Date timestamp;
    private List<String> errors;
    private Integer status;


    public ErrorDto(Date timestamp, List<String> errors, Integer status) {
        this.timestamp = timestamp;
        this.errors = errors;
        this.status = status;
    }

}
