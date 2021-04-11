package com.br.brasilprev.customers.core.dto.viacep.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Data Transfer Object Endereço Via Cep Response
 * @author Danillo Lima
 * @since 01/04/2021s
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoViaCepResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String ibge;
    private String gia;
}
