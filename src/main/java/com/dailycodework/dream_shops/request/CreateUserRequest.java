package com.dailycodework.dream_shops.request;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;


@Data
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    @NaturalId
    private String email;
    private String password;


}



