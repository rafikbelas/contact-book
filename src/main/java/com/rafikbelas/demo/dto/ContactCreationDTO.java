package com.rafikbelas.demo.dto;

import java.time.LocalDate;

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
    private LocalDate dateOfBirth;
    private AddressCreationDTO address;
}