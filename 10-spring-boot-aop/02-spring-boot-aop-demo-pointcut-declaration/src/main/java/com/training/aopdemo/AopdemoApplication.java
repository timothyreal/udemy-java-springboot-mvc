package com.training.aopdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.aopdemo.dao.AccountDAO;
import com.training.aopdemo.dao.MembershipDAO;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		return runner -> {
			this.demoTheBeforeAdvice(accountDAO, membershipDAO);
		};
	}

	void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		Account account = new Account();
		accountDAO.addAccount(account, true);
		accountDAO.doWork();

		accountDAO.setName("AccountDAO");
		accountDAO.setServiceCode("AccountDAOCODE");
		accountDAO.getName();
		accountDAO.getServiceCode();


		membershipDAO.addMember();
		membershipDAO.addSomething();
	}

}
