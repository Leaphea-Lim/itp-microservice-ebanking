package co.istad.customer.application.listener;


import co.istad.customer.application.mapper.CustomerApplicationMapper;
import co.istad.customer.data.entity.CustomerEntity;
import co.istad.customer.data.entity.CustomerSegmentEntity;
import co.istad.customer.data.repository.CustomerRepository;
import co.istad.customer.data.repository.CustomerSegmentRepository;
import co.istad.customer.domain.event.CustomerCreatedEvent;
import co.istad.customer.domain.event.CustomerPhoneNumberChangedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("customer-group")
public class CustomerListener {

    private final CustomerRepository customerRepository;
    private final CustomerApplicationMapper customerApplicationMapper;
    private final CustomerSegmentRepository customerSegmentRepository;

    @EventHandler
    public void on(CustomerCreatedEvent customerCreatedEvent){
        log.info("on CustomerCreatedEvent: {} ", customerCreatedEvent);

        CustomerEntity customerEntity =
                customerApplicationMapper.customerCreatedEventToCustomerEntity(customerCreatedEvent);

        customerEntity.getAddress().setCustomer(customerEntity);
        customerEntity.getContact().setCustomer(customerEntity);
        customerEntity.getKyc().setCustomer(customerEntity);
        customerEntity.setPhoneNumber(customerCreatedEvent.phoneNumber());

        CustomerSegmentEntity customerSegmentEntity = customerSegmentRepository
                .findById(customerCreatedEvent.customerSegmentId().customerSegmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Segment Not Found"));

        customerEntity.setCustomerSegment(customerSegmentEntity);

        customerRepository.save(customerEntity);

    }

    @EventHandler
    public void on(CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent){
        log.info("on CustomerPhoneNumberChangedEvent: {} ", customerPhoneNumberChangedEvent);

        CustomerEntity customerEntity = customerRepository
                .findById(customerPhoneNumberChangedEvent
                        .customerId()
                        .getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"customer not found"));

        customerEntity.setPhoneNumber(customerPhoneNumberChangedEvent.phoneNumber());
        customerRepository.save(customerEntity);
    }

}
