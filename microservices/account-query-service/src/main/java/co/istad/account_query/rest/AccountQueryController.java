package co.istad.account_query.rest;

import co.istad.account_query.applicationservice.dto.AccountQueryResponse;
import co.istad.account_query.applicationservice.ports.input.service.AccountQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("{accountId}")
    Mono<AccountQueryResponse> getByAccountId(@PathVariable UUID accountId){

        return accountQueryService.getByAccountId(accountId);

    }
}
