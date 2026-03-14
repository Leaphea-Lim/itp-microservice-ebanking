package co.istad.customer.application.projection;

import co.istad.customer.application.dto.query.CustomerPageResponse;
import co.istad.customer.application.dto.query.CustomerResponse;
import co.istad.customer.application.mapper.CustomerApplicationMapper;
import co.istad.customer.data.entity.CustomerEntity;
import co.istad.customer.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
//handle when axon queryGateway is request
public class CustomerQueryHandler {

    private final CustomerRepository customerRepository;
    private final CustomerApplicationMapper customerApplicationMapper;

    @QueryHandler
//    public List<CustomerResponse> handle(GetCustomerQuery query){
    public CustomerPageResponse handle(GetCustomerQuery query) {


        Pageable pageable = PageRequest.of(
                query.getPageNumber(),
                query.getPageSize(),
                Sort.by(Sort.Direction.DESC, "dob")
        );

        Page<CustomerEntity> page = customerRepository.findAll(pageable);

        List<CustomerResponse> content = page
//                .stream()
                .map(customerApplicationMapper::customerEntityToCustomerResponse)
//                .toList()
                .getContent();

          return new CustomerPageResponse(
                  content,
                  page.getNumber(),
                  page.getSize(),
                  page.getTotalElements(),
                  page.getTotalPages()
          );
//        return customers.stream().map(customerApplicationMapper::customerEntityToCustomerResponse).toList();
    }
}
