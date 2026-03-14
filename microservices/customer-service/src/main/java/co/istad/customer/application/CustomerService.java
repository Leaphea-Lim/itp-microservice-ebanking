package co.istad.customer.application;

import co.istad.customer.application.dto.create.CreateCustomerRequest;
import co.istad.customer.application.dto.create.CreateCustomerResponse;
import co.istad.customer.application.dto.query.CustomerResponse;
import co.istad.customer.application.dto.update.ChangePhoneNumberRequest;
import co.istad.customer.application.dto.update.ChangePhoneNumberResponse;

import java.util.UUID;

public interface CustomerService {

    ChangePhoneNumberResponse changePhoneNumber(UUID customerId, ChangePhoneNumberRequest changePhoneNumberRequest);
    CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerResponse getCustomerById(UUID customerId);
}
