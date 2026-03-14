package co.istad.customer.domain.aggregate;

import co.istad.common.domain.valueObject.CustomerId;
import co.istad.common.domain.valueObject.CustomerSegmentId;
import co.istad.customer.domain.command.ChangePhoneNumberCommand;
import co.istad.customer.domain.command.CreateCustomerCommand;
import co.istad.customer.domain.event.CustomerCreatedEvent;
import co.istad.customer.domain.event.CustomerPhoneNumberChangedEvent;
import co.istad.customer.domain.valueobject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.util.List;

@Aggregate(snapshotTriggerDefinition = "customerSnapshotTriggerDefinition")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Slf4j
public class CustomerAggregate {
    //value object
    @AggregateIdentifier
    private CustomerId customerId;
    private CustomerName name;
    private CustomerEmail email;
    private CustomerGender gender;
    private LocalDate dob;
    private Kyc kyc;
    private Address address;
    private Contact contact;
    private String phoneNumber;
    private CustomerSegmentId customerSegmentId;
    private List<String> failureMessages;


    //domain logic for creating customer
    //constructure CommandHandler require for first event
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand){
        //validate
        //perform domain logic
        //validate email
        //validate phone number

        //publish event -> CustomerCreatedEvent
        CustomerCreatedEvent customerCreatedEvent =
                CustomerCreatedEvent.builder()
                        .customerId(createCustomerCommand.customerId())
                        .name(createCustomerCommand.name())
                        .email(createCustomerCommand.email())
                        .gender(createCustomerCommand.gender())
                        .dob(createCustomerCommand.dob())
                        .phoneNumber(createCustomerCommand.phoneNumber())
                        .kyc(createCustomerCommand.kyc())
                        .address(createCustomerCommand.address())
                        .contact(createCustomerCommand.contact())
                        .customerSegmentId(createCustomerCommand.customerSegmentId())

                        .build();
        log.info("Creating customer with command: {}", createCustomerCommand);
        //call axon, it will serksa Event-sourcing
        AggregateLifecycle.apply(customerCreatedEvent);

    }

    @CommandHandler
    public void handle(ChangePhoneNumberCommand changePhoneNumberCommand) {
        log.info("Handle change phone number command: {}", changePhoneNumberCommand);

        CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent = CustomerPhoneNumberChangedEvent.builder()
                .customerId(changePhoneNumberCommand.customerId())
                .phoneNumber(changePhoneNumberCommand.phoneNumber())
                .build();

        AggregateLifecycle.apply(customerPhoneNumberChangedEvent); //<- an event is applied
    }

    @EventSourcingHandler //Updates the aggregate’s internal state when an event is applied.
    public void on(CustomerCreatedEvent customerCreatedEvent){
        this.customerId = customerCreatedEvent.customerId();
        //base on event
        this.name = customerCreatedEvent.name();
        this.gender = customerCreatedEvent.gender();
        this.email = customerCreatedEvent.email();
        this.dob = customerCreatedEvent.dob();
        this.phoneNumber = customerCreatedEvent.phoneNumber();
        this.kyc = customerCreatedEvent.kyc();
        this.address = customerCreatedEvent.address();
        this.contact = customerCreatedEvent.contact();
        this.customerSegmentId = customerCreatedEvent.customerSegmentId();
    }

    @EventSourcingHandler
    public void on(CustomerPhoneNumberChangedEvent customerPhoneNumberChangedEvent) {
        this.customerId = customerPhoneNumberChangedEvent.customerId();
        this.phoneNumber = customerPhoneNumberChangedEvent.phoneNumber();
    }
}
