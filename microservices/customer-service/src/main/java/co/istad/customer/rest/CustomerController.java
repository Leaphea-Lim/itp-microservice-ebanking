package co.istad.customer.rest;

import co.istad.customer.application.CustomerService;
import co.istad.customer.application.dto.create.CreateCustomerRequest;
import co.istad.customer.application.dto.create.CreateCustomerResponse;
import co.istad.customer.application.dto.query.CustomerResponse;
import co.istad.customer.application.dto.update.ChangePhoneNumberRequest;
import co.istad.customer.application.dto.update.ChangePhoneNumberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    @PostMapping
    public CreateCustomerResponse createCustomerResponse(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){
        log.info("Create Customer: {}", createCustomerRequest);

        return customerService.createCustomer(createCustomerRequest);
    }

    @PutMapping("/{customerId}/phone-number")
    public ChangePhoneNumberResponse changePhoneNumber(
            @PathVariable UUID customerId,
            @Valid @RequestBody ChangePhoneNumberRequest changePhoneNumberRequest) {
        log.info("Received request to change phone number: {}", changePhoneNumberRequest);
        return customerService.changePhoneNumber(customerId, changePhoneNumberRequest);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getCustomerById(@PathVariable UUID customerId) {
        return customerService.getCustomerById(customerId);
    }

}
