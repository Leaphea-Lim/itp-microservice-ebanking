package co.istad.account.dataaccess.repository;

import co.istad.account.dataaccess.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<BranchEntity, UUID> {
}
