package com.br.brasilprev.customers.dataprovider.apis.viacep;

import com.br.brasilprev.customers.core.dto.viacep.response.EnderecoViaCepResponse;
import com.br.brasilprev.customers.dataprovider.adapter.interfaces.viacep.AddressConverter;
import com.br.brasilprev.customers.error.exception.BadRequestErrorException;
import com.br.brasilprev.customers.error.exception.InternalServerErrorException;
import com.br.brasilprev.customers.error.exception.NotFoundErrorException;
import com.br.brasilprev.customers.utils.LogUtils;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ViaCepWebClient {

    private final WebClient webClient;
    private String MIME_TYPE;
    private String API_BASE_URL = "https://viacep.com.br/ws";

    @Autowired
    public ViaCepWebClient() {
        this.MIME_TYPE = "Authorization";

        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120000)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(60000, TimeUnit.MILLISECONDS));
                });
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, this.MIME_TYPE)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(50 * 1024 * 1024))
                        .build())
                .filter(LogUtils.logRequest())
                .build();
    }

    public Mono findAddressByZipCode(String zipCode) {
        Mono<EnderecoViaCepResponse> enderecoViaCepResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{cep}/json")
                        .build(zipCode))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    response.toEntity(String.class).subscribe(error -> log.error("ViaCep findAddressByCep error {}", error));
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.just(new NotFoundErrorException("Cep não encontrado"));
                    } else {
                        return Mono.just(new BadRequestErrorException("Bad request"));
                    }
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    response.toEntity(String.class).subscribe(error -> log.error("ViaCep findAddressByCep error {}", error));
                    return Mono.just(new InternalServerErrorException("Aguarde um momento, nosso sistema deu problema e já já estará de volta!"));
                })
                .bodyToMono(EnderecoViaCepResponse.class);
        return AddressConverter.converterAddressViaCepToAddressResponse(enderecoViaCepResponse);
    }

}
