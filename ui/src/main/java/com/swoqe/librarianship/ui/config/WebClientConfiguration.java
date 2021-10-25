package com.swoqe.librarianship.ui.config;

import com.swoqe.librarianship.dto.security.AuthResponse;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebClientConfiguration {

    @Value("${web_client.base_host}")
    private String BASE_HOST;

    @Value("${web_client.timeout}")
    public int TIMEOUT;

    private final AuthResponse authentication;

    @Bean
    public WebClient webClientWithTimeout() {
        final var tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder()
                .baseUrl(BASE_HOST)
                .filter(logFilter())
                .filter(ExchangeFilterFunction.ofRequestProcessor(
                                request -> Mono.just(ClientRequest.from(request)
                                        .header(HttpHeaders.AUTHORIZATION, Objects.requireNonNullElse(authentication.getToken(), ""))
                                        .build())
                ))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    private ExchangeFilterFunction logFilter() {
        return (clientRequest, next) -> {
            log.info("External Request to {}", clientRequest.url());
            return next.exchange(clientRequest);
        };
    }

}