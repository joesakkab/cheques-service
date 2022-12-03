package com.progressoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@Configuration
//@EnableJpaRepositories

//TODO: add mock mvc as testing method
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
//	CommandLineRunner commandLineRunner() {
//		return args -> {
//			ChequeRequest dto = new ChequeRequest();
//			dto.setAmount(new BigDecimal(2000));
//			dto.setNumber("00012345");
//			dto.setDigit("004");
//
//			dto.setDrawerAccountEntity(new AccountDto());
//			dto.getDrawerAccountEntity().setAccountNumber("01234567");
//			dto.getDrawerAccountEntity().setBranchCode("1236");
//			dto.getDrawerAccountEntity().setBankCode("05");
//
//			dto.setPayeeAccountEntity(new AccountDto());
//			dto.getPayeeAccountEntity().setAccountNumber("52169856");
//			dto.getPayeeAccountEntity().setBranchCode("5631");
//			dto.getPayeeAccountEntity().setBankCode("05");
////
//			dto.setPostingDate(LocalDate.of(2022, 11, 30));
//
//			System.out.println(dto);
//			ChequeMapper mapper = Mappers.getMapper(ChequeMapper.class);
//			ChequeEntity entity = mapper.map(dto);
//			System.out.println("Entity is: \n" + entity.toString());
//		};
//	}

}
