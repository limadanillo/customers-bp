package com.br.brasilprev.customers.core.useCases;

import com.br.brasilprev.customers.dataprovider.apis.viacep.ViaCepWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AddressServiceImp implements AddressService{

    @Autowired
    private ViaCepWebClient viaCepWebClient;

    @Override
    public Mono findAdressByZipCodeInViaCep(String zipCode) {
        return viaCepWebClient.findAddressByZipCode(zipCode);
    }
}
