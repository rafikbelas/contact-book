package com.rafikbelas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactCreationDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private AddressCreationDTO address;
}