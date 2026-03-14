package co.istad.customer.data.init;

import co.istad.customer.data.entity.CustomerSegmentEntity;
import co.istad.customer.data.repository.CustomerSegmentRepository;
import co.istad.customer.domain.valueobject.CustomerSegmentType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerSegmentInit {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void initCustomerSegment() {
        if (customerSegmentRepository.count() == 0) {

            CustomerSegmentEntity business = new CustomerSegmentEntity();
            business.setCustomerSegmentId(UUID.randomUUID());
            business.setCustomerSegmentType(CustomerSegmentType.BUSINESS);

            CustomerSegmentEntity student = new CustomerSegmentEntity();
            student.setCustomerSegmentId(UUID.randomUUID());
            student.setCustomerSegmentType(CustomerSegmentType.STUDENT);

            CustomerSegmentEntity normal = new CustomerSegmentEntity();
            normal.setCustomerSegmentId(UUID.randomUUID());
            normal.setCustomerSegmentType(CustomerSegmentType.NORMAL);

            CustomerSegmentEntity vip = new CustomerSegmentEntity();
            vip.setCustomerSegmentId(UUID.randomUUID());
            vip.setCustomerSegmentType(CustomerSegmentType.VIP);

            customerSegmentRepository.saveAll(List.of(normal, student, vip, business));
            log.info("Customer segments initialized successfully");
        }
    }
}