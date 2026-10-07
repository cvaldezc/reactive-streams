package rx.chris.ticketreservation.service;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import rx.chris.ticketreservation.exception.TicketReservationException;
import rx.chris.ticketreservation.model.Reservation;
import rx.chris.ticketreservation.model.ReservationRequest;
import rx.chris.ticketreservation.model.dto.EventResponse;
import rx.chris.ticketreservation.model.dto.WalletResponse;
import rx.chris.ticketreservation.repository.ReservationEntity;
import rx.chris.ticketreservation.repository.ReservationMapper;
import rx.chris.ticketreservation.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketReservationService {

    private final WebClient eventWebClient;
    private final WebClient walletWebClient;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final CircuitBreakerRegistry registry;

    public Mono<Reservation> createTicketReservation(ReservationRequest request) {
        return Mono.zip(getEventResponse(request), getWalletResponse(request))
                .filter(ob -> ob.getT1().hasStock() && isAvailableBalance(ob.getT1(), ob.getT2(), request))
                .switchIfEmpty(Mono.error(() ->  new TicketReservationException("Fondos insuficientes o sin stock")))
                .flatMap(ob -> {
                    var unsaved = new ReservationEntity();
                    unsaved.setEventId(request.eventId());
                    unsaved.setUserId(request.userId());
                    unsaved.setTotalAmount(ob.getT1().unitPrice().multiply(BigDecimal.valueOf(request.quantity())));
                    log.info("unsaved {}", unsaved);
                    return reservationRepository.save(unsaved);
                })
                .map(reservationMapper::toReservation);
                //.map(e -> new Reservation(e.getId(), e.getEventId(), e.getUserId(), e.getTotalAmount()));
    }

    private Boolean isAvailableBalance(EventResponse event, WalletResponse wallet, ReservationRequest request) {
        var total = event.unitPrice().multiply(BigDecimal.valueOf(request.quantity()));
        log.info("total is {}", total);
        return total.compareTo(wallet.currentBalance()) <= 0;
    }

    private Mono<EventResponse> getEventResponse(ReservationRequest request) {
        return eventWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/events/{eventId}/availability")
                        .queryParam("qty", request.quantity())
                        .build(request.eventId()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(EventResponse.class)
                .doOnNext(event -> log.info("Event Response: {}", event));
    }

    private Mono<WalletResponse> getWalletResponse(ReservationRequest request) {
        return walletWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/wallets/{userId}/balance")
                        .build(request.userId()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(WalletResponse.class)
                .timeout(Duration.ofSeconds(2))
                .transformDeferred(CircuitBreakerOperator.of(registry.circuitBreaker("wallet")))
                .onErrorResume(t -> fallback())
                .doOnNext(response -> log.info("service Wallet {}", response));
    }

    private Mono<WalletResponse> fallback() {
        log.error("Fallback for wallet");
        return Mono.just(new WalletResponse(BigDecimal.ZERO));
    }


}
