package co.istad.account.dataaccess.init;

import co.istad.account.dataaccess.entity.BranchEntity;
import co.istad.account.dataaccess.repository.BranchRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class BranchInit {

    private final BranchRepository branchRepository;

    @PostConstruct
    public void initBranch() {
        if (branchRepository.count() == 0) {
            BranchEntity phnomPenh = new BranchEntity();
            phnomPenh.setBranchId(UUID.randomUUID());
            phnomPenh.setName("Phnom Penh Branch");
            phnomPenh.setOpening(true);

            BranchEntity siemReap = new BranchEntity();
            siemReap.setBranchId(UUID.randomUUID());
            siemReap.setName("Siem Reap Branch");
            siemReap.setOpening(true);

            BranchEntity battambang = new BranchEntity();
            battambang.setBranchId(UUID.randomUUID());
            battambang.setName("Battambang Branch");
            battambang.setOpening(true);

            branchRepository.saveAll(List.of(phnomPenh, siemReap, battambang));
            log.info("Branches initialized successfully");
        }
    }
}