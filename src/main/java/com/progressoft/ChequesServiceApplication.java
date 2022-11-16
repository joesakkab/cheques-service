package com.progressoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
//			AccountPostAndPutDto account1 = new AccountPostAndPutDto(
//					"07",
//					"002",
//					"000003401876");
//			AccountPostAndPutDto account2 = new AccountPostAndPutDto(
//					"02",
//					"003",
//					"0000000010220474");
//			AccountPostAndPutDto account3 = new AccountPostAndPutDto(
//					"09",
//					"016",
//					"0000087123964");
//
//			accountRepository.save(account1);
//			accountRepository.save(account2);
//			accountRepository.save(account3);
//
//			ChequePostDto c1 = new ChequePostDto(
//					new BigDecimal("10000"),
//					"0087829736",
//					"001",
//					account1,
//					account2
//			);
//			ChequePostDto c2 = new ChequePostDto(
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
