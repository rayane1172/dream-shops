package com.dailycodework.dream_shops.dto;

import com.dailycodework.dream_shops.model.Cart;
import com.dailycodework.dream_shops.model.Order;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.List;


@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;

}
