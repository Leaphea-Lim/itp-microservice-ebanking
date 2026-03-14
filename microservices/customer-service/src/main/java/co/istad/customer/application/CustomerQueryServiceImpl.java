package co.istad.customer.application;

import co.istad.customer.application.dto.query.CustomerPageResponse;
import co.istad.customer.application.projection.GetCustomerQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService{

    private final QueryGateway queryGateway;
    private final EventStore eventStore;


    @Override
    public List<?> getCustomerHistory(UUID customerId){
        return eventStore.readEvents(customerId.toString())
                .asStream()
                .map(Message::getPayload)
                .toList();
    }

    @Override
//    public List<CustomerResponse> getAllCustomers(int pageNumber, int pageSize) {
    public CustomerPageResponse getAllCustomers(int pageNumber, int pageSize) {
        GetCustomerQuery query =
                new GetCustomerQuery(pageNumber, pageSize);

//        getCustomerQuery.setPageNumber(pageNumber);
//        getCustomerQuery.setPageSize(pageSize);

//        return queryGateway.query(getCustomerQuery, ResponseTypes.multipleInstancesOf(CustomerResponse.class)).join();
        return queryGateway.query(
              query, CustomerPageResponse.class
        ).join();
    }
}
