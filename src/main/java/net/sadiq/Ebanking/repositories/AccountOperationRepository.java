package net.sadiq.Ebanking.repositories;

import net.sadiq.Ebanking.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long>{
    List<AccountOperation> findByBankAccountId(String accountId);
}
