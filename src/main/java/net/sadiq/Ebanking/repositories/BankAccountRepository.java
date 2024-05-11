package net.sadiq.Ebanking.repositories;

import net.sadiq.Ebanking.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
