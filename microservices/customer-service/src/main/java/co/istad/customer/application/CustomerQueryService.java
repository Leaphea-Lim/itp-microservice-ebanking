package co.istad.customer.application;

import co.istad.customer.application.dto.query.CustomerPageResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CustomerQueryService {

//    List<CustomerResponse> getAllCustomers(int pageNumber, int pageSize);
      CustomerPageResponse getAllCustomers(int pageNumber, int pageSize);
      List<?> getCustomerHistory(UUID customerId);
}
