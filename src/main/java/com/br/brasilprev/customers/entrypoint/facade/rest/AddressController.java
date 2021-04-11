package com.br.brasilprev.customers.entrypoint.facade.rest;

import com.br.brasilprev.customers.core.constrain.ZipCodeConstraint;
import com.br.brasilprev.customers.core.useCases.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Class Role Controller
 *
 * @author Danillo Lima
 * @since 09/04/2021
 */
@Tag(name="Address")
@Slf4j
@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
@Validated
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono findByZipCodeInViaCep(@RequestParam @ZipCodeConstraint String zipCode) {
        return addressService.findAdressByZipCodeInViaCep(zipCode);
    }
}
