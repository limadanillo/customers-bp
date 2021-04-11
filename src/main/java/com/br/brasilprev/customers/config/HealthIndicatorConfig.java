package com.br.brasilprev.customers.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Class Health Indicator Configuration
 * @author Danillo Lima
 * @since 07/04/2021
 */
@Slf4j
@Component
public class HealthIndicatorConfig implements ReactiveHealthIndicator {

    @Override
    public Mono<Health> health() {
        return checkDownstreamServiceHealth().onErrorResume(
                ex -> Mono.just(new Health.Builder().down(ex).build())
        );
    }

    private Mono<Health> checkDownstreamServiceHealth() {
        return Mono.just(new Health.Builder().up().build());
    }
}