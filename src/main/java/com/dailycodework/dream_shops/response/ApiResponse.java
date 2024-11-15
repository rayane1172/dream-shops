package com.dailycodework.dream_shops.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse { // to return data to our front-end application
    private String message;
    private Object data;  //todo -> to send a response in term of data object

}
