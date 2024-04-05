package com.example.SunBase.RequestDtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AddCustomerRequest {

    private  String first_name;
    private  String last_name;
    private  String  street;
    private  String  address;
    private  String   city;
    private  String   state;
    private  String   email;
    private  String phone;
}
