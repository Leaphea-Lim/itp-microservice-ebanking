package co.istad.account.infrastructor.client;

import co.istad.account.infrastructor.client.dto.CustomerResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange
public interface CustomerClient {

    @GetExchange("/api/customers/{customerId}")
    CustomerResponse getCustomerById(@PathVariable UUID customerId);
}
