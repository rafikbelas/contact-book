package com.rafikbelas.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
}
