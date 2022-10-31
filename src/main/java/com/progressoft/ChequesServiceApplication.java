package com.progressoft;

import com.progressoft.model.Account;
import com.progressoft.model.Cheque;
import com.progressoft.repositories.AccountRepository;
import com.progressoft.repositories.ChequeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

//@Configuration
//@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class ChequesServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChequesServiceApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner() {
//		return args -> {};
//	}
//	@Bean
//	CommandLineRunner commandLineRunner(ChequeRepository chequeRepository, AccountRepository accountRepository) {
//		return args -> {
//			Account account1 = new Account(
//					"07",
//					"002",
//					"000003401876");
//			Account account2 = new Account(
//					"02",
//					"003",
//					"0000000010220474");
//			Account account3 = new Account(
//					"09",
//					"016",
//					"0000087123964");
//
//			accountRepository.save(account1);
//			accountRepository.save(account2);
//			accountRepository.save(account3);
//
//			Cheque c1 = new Cheque(
//					new BigDecimal("10000"),
//					"0087829736",
//					"001",
//					account1,
//					account2
//			);
//			Cheque c2 = new Cheque(
//					new BigDecimal("64328.35"),
//					"0005612",
//					"009",
//					account3,
//					account1
//			);
//			chequeRepository.save(c1);
//			chequeRepository.save(c2);
//		};
//	}

}
