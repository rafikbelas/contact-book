package com.rafikbelas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContactDTO {
    private String fullName;
    private String dateOfBirth;
    private AddressDTO address;
}
