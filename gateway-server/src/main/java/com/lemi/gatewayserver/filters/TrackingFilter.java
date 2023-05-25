package com.lemi.gatewayserver.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Order(1)
@RequiredArgsConstructor
@Component
public class TrackingFilter implements GlobalFilter {

    private final FilterUtils filterUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var requestHeader = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeader)) {
            log.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId(requestHeader));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange, correlationID);
            log.debug("tmx-correlation-id generated in tracking filter: {}. ", correlationID);
        }
        return chain.filter(exchange);
    }

    private Boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return filterUtils.getCorrelationId(requestHeaders) != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
