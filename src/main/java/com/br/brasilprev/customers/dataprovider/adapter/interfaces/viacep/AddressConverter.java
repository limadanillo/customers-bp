package com.br.brasilprev.customers.dataprovider.adapter.interfaces.viacep;

import com.br.brasilprev.customers.core.dto.response.AddressResponse;
import com.br.brasilprev.customers.core.dto.viacep.response.EnderecoViaCepResponse;
import reactor.core.publisher.Mono;

public abstract class AddressConverter {
    public final static Mono converterAddressViaCepToAddressResponse(Mono<EnderecoViaCepResponse> enderecoViaCepResponse){
        return enderecoViaCepResponse.map(response -> {
            return AddressResponse.builder()
                    .city(response.getLocalidade())
                    .state(response.getUf())
                    .street(response.getLogradouro())
                    .complement(response.getComplemento())
                    .neighborhood(response.getBairro())
                    .zipCode(response.getCep())
                    .build();
        });
    }
}
