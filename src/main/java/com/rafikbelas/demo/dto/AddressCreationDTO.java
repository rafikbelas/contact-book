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
public class AddressCreationDTO {
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
}
