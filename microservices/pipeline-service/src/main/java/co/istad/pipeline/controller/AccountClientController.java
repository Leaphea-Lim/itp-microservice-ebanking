package co.istad.pipeline.controller;

import co.istad.pipeline.client.AccountClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/client/accounts")
//@RequiredArgsConstructor
public class AccountClientController {

    private final AccountClient accountClient;
    private final CircuitBreaker circuitBreaker;

    //todo: if you want to have another service and can communicate as resilience
    public AccountClientController(AccountClient accountClient,
                                   CircuitBreakerRegistry registry){
        this.accountClient = accountClient;
        circuitBreaker = registry.circuitBreaker("account");
    }


    @GetMapping("/secured")
//    @CircuitBr eaker(name = "accountClientCB", fallbackMethod = "fallback")
    public Map<String, Object> getSecuredData(){
//        return accountClient.getSecuredData();
//        log.debug("debug getSecuredData");
//        throw new RuntimeException("Error internal ");
        try{
            return circuitBreaker.executeSupplier(accountClient::getSecuredData);
        } catch (CallNotPermittedException e) {
            return Map.of("data: ", e.getMessage());
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Map.of("data: ", e.getMessage());
        }
    }

//   todo: using fallback + CB (even if it not yet fail, it will still be open)
//    public Map<String, Object> fallback(Throwable ex) {
//        log.error("Account service call failed: {}", ex.getMessage(), ex);
//        return Map.of(
//                "status", "fallback",
//                "message", "Account service is currently unavailable. Please try again later."
//        );
//    }
//
}
