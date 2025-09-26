package com.example.user.client;

import com.example.user.exception.WebClientExceptionMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

@Service
public class BorrowClient {
    private final WebClient webClient;
    private final WebClientExceptionMapper exceptionMapper;
    private final String serviceName;

    private final String BORROW_SERVICE_URL;

    public BorrowClient(WebClient webClient,
                        WebClientExceptionMapper exceptionMapper,
                        @Value("${spring.application.name}") String serviceName,
                        @Value("${service.borrow.url}") String borrowServiceUrl) {
        this.webClient = webClient;
        this.exceptionMapper = exceptionMapper;
        this.serviceName = serviceName;
        this.BORROW_SERVICE_URL = borrowServiceUrl;
    }

    public Integer countBooksBorrowedByUser(UUID userId) {
        return webClient
                .get()
                .uri(this.BORROW_SERVICE_URL + "api/v1/user/" + userId + "/countBorrowed")
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> exceptionMapper.mapError(response, serviceName)
                )
                .bodyToMono(Integer.class)
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(500))
                                .filter(this::isRetryableException)
                )
                .block();
    }

    private boolean isRetryableException(Throwable throwable) {
        return throwable instanceof WebClientResponseException &&
                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
    }
}
