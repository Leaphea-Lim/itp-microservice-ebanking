package co.istad.customer.rest;

import co.istad.customer.application.CustomerQueryService;
import co.istad.customer.application.dto.query.CustomerPageResponse;
import co.istad.customer.application.dto.query.CustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerQueryController {

    //axon's gateway
//    private final QueryGateway queryGateway;
    private final CustomerQueryService customerQueryService;

    @GetMapping
//    public List<CustomerResponse> getAllCustomers(
    public CustomerPageResponse getAllCustomers(

            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "5", required = false) int pageSize
    ){
        return customerQueryService.getAllCustomers(pageNumber, pageSize);
    }

    @GetMapping("/{customerId}/history")
    public List<?> getCustomerHistory(@PathVariable UUID customerId){
        return customerQueryService.getCustomerHistory(customerId);
    }

//    @GetMapping("/{customerId}")
//    public CustomerResponse getCustomerById(@PathVariable UUID customerId) {
//        return customerQueryService(customerId);
//    }
}
