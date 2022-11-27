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
//	CommandLineRunner commandLineRunner() {
//		return args -> {
//			ChequePostDto dto = new ChequePostDto();
//			dto.setAmount(new BigDecimal(2000));
//			dto.setNumber("00012345");
//			dto.setDigit("004");
//
//			dto.setDrawerAccount(new AccountDto());
//			dto.getDrawerAccount().setAccountNumber("01234567");
//			dto.getDrawerAccount().setBranchCode("1236");
//			dto.getDrawerAccount().setBankCode("05");
//
//			dto.setPayeeAccount(new AccountDto());
//			dto.getPayeeAccount().setAccountNumber("52169856");
//			dto.getPayeeAccount().setBranchCode("5631");
//			dto.getPayeeAccount().setBankCode("05");
////
//			dto.setPostingDate(LocalDate.of(2022, 11, 30));
//
//			System.out.println(dto);
//			ChequeMapper mapper = Mappers.getMapper(ChequeMapper.class);
//			Cheque entity = mapper.map(dto);
//			System.out.println("Entity is: \n" + entity.toString());
//		};
//	}

}
