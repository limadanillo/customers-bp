package com.br.brasilprev.customers.core.useCases;

import reactor.core.publisher.Mono;

public interface AddressService {
    Mono findAdressByZipCodeInViaCep(String zipCode);
}
