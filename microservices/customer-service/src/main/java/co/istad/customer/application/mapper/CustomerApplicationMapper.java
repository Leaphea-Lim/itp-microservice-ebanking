package co.istad.customer.application.mapper;

import co.istad.common.domain.valueObject.CustomerId;
import co.istad.customer.application.dto.create.CreateCustomerRequest;
import co.istad.customer.application.dto.query.CustomerResponse;
import co.istad.customer.data.entity.CustomerEntity;
import co.istad.customer.domain.command.CreateCustomerCommand;
import co.istad.customer.domain.event.CustomerCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerApplicationMapper {

    CustomerResponse customerEntityToCustomerResponse(CustomerEntity customerEntity);

    CreateCustomerCommand
    createCustomerRequestToCreateCustomerCommand(CustomerId customerId,
                                                 CreateCustomerRequest createCustomerRequest);


    @Mapping(source = "customerId.value", target = "customerId")

    CustomerEntity customerCreatedEventToCustomerEntity(CustomerCreatedEvent customerCreatedEvent);
}
