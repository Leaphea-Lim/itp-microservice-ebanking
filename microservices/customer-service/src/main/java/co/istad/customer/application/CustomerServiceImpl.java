package co.istad.customer.application;

import co.istad.common.domain.valueObject.CustomerId;
import co.istad.customer.application.dto.create.CreateCustomerRequest;
import co.istad.customer.application.dto.create.CreateCustomerResponse;
import co.istad.customer.application.dto.query.CustomerResponse;
import co.istad.customer.application.dto.update.ChangePhoneNumberRequest;
import co.istad.customer.application.dto.update.ChangePhoneNumberResponse;
import co.istad.customer.application.mapper.CustomerApplicationMapper;
import co.istad.customer.data.repository.CustomerRepository;
import co.istad.customer.domain.command.ChangePhoneNumberCommand;
import co.istad.customer.domain.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerApplicationMapper customerMapper;
    private final CommandGateway commandGateway;
    private final CustomerRepository customerRepository;


    @Override
    public ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest) {

        // 1. Transfer data from request to command
        ChangePhoneNumberCommand changePhoneNumberCommand = ChangePhoneNumberCommand.builder()
                .customerId(new CustomerId(customerId))
                .phoneNumber(changePhoneNumberRequest.phoneNumber())
                .build();
        log.info("ChangePhoneNumberCommand: {}", changePhoneNumberCommand);

//        UUID result = commandGateway.sendAndWait(changePhoneNumberCommand);
        commandGateway.sendAndWait(changePhoneNumberCommand);

        return ChangePhoneNumberResponse.builder()
                //
                .customerId(customerId)
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .message("Phone number changed successfully")
                .build();
    }

    @Override
    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {

        // 1. Transfer data from request to command
        CreateCustomerCommand createCustomerCommand = customerMapper
                .createCustomerRequestToCreateCustomerCommand(new
                        CustomerId(UUID.randomUUID()), createCustomerRequest);
        log.info("CreateCustomerCommand: {}", createCustomerCommand);

        // 2. Invoke and handle Axon command gateway
        CustomerId result = commandGateway.sendAndWait(createCustomerCommand);
        log.info("CommandGateway Result: {}", result);

        //if no need log
//        commandGateway.sendAndWait(createCustomerCommand);

        return CreateCustomerResponse.builder()
                .customerId(createCustomerCommand.customerId().getValue())
                .message("Customer saved successfully")
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::customerEntityToCustomerResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Customer not found"));
    }

}
