package com.br.brasilprev.customers.core.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private String city;
    private String state;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;
}
