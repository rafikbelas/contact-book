package com.rafikbelas.demo.dto;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String address1;
    // Could be blank
    private String address2;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
}
