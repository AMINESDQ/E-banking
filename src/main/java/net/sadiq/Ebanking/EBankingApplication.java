package net.sadiq.Ebanking;

import net.sadiq.Ebanking.entity.AccountOperation;
import net.sadiq.Ebanking.entity.CurrentAccount;
import net.sadiq.Ebanking.entity.Customer;
import net.sadiq.Ebanking.entity.SavingAccount;
import net.sadiq.Ebanking.enums.AccountStatus;
import net.sadiq.Ebanking.enums.OperationType;
import net.sadiq.Ebanking.repositories.AccountOperationRepository;
import net.sadiq.Ebanking.repositories.BankAccountRepository;
import net.sadiq.Ebanking.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationRepository operationRepository) {
		return args -> {
			Stream.of("amine", "sadiq", "mohamed", "oussama", "imad").forEach(name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerRepository.save(customer);
					});

			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setOverDraft(9000);
				currentAccount.setCustomer(customer);
				currentAccount.setBalance(Math.random() * 90000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				bankAccountRepository.save(currentAccount);
			});

			customerRepository.findAll().forEach(customer -> {
				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setInterestRate(4.5);
				savingAccount.setCustomer(customer);
				savingAccount.setBalance(Math.random() * 90000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setId(UUID.randomUUID().toString());
				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(bankAccount -> {
				Stream.of(1, 2, 3).forEach(i -> {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setAmount(Math.random() * 9000);
					accountOperation.setOperationDate(new Date());
					accountOperation.setType(i % 2 == 0 ? OperationType.CREDIT:OperationType.DEBIT);
					accountOperation.setDescription("description");
					accountOperation.setBankAccount(bankAccount);
					operationRepository.save(accountOperation);
				});
			});


		};

	}
}
