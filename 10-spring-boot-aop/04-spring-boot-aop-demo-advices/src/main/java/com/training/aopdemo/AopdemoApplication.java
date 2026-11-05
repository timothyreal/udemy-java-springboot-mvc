package com.training.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.training.aopdemo.dao.AccountDAO;
import com.training.aopdemo.dao.MembershipDAO;
import com.training.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		return runner -> {
			// this.demoTheBeforeAdvice(accountDAO, membershipDAO);
			// this.demoTheAfterReturningAdvice(accountDAO);
			// this.demoTheAfterThrowingAdvice(accountDAO);
			// this.demoTheAfterAdvice(accountDAO);
			// this.demoTheAroundAdvice(trafficFortuneService);
			// this.demoTheAroundAdviceHandleException(trafficFortuneService);
			this.demoTheAroundAdviceRethrowException(trafficFortuneService);
		};
	}

	private void addAccounts(AccountDAO accountDAO) {
		Account account1 = new Account("Rizal", "Hero");
		Account account2 = new Account("Andres", "Katipunero");
		Account account3 = new Account("Quezon", "Pangulo");
		accountDAO.addAccount(account1, true);
		accountDAO.addAccount(account2, true);
		accountDAO.addAccount(account3, true);
	}

	void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		Account account = new Account("Rizal", "Hero");
		accountDAO.addAccount(account, true);
		accountDAO.doWork();

		accountDAO.setName("AccountDAO");
		accountDAO.setServiceCode("AccountDAOCODE");
		accountDAO.getName();
		accountDAO.getServiceCode();

		membershipDAO.addMember();
		membershipDAO.addSomething();
	}

	void demoTheAfterReturningAdvice(AccountDAO accountDAO) {
		this.addAccounts(accountDAO);

		List<Account> accounts = accountDAO.findAccounts();
		System.out.println("-----");
		System.out.println("Accounts: " + accounts + "\n");
	}

	void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		this.addAccounts(accountDAO);

		List<Account> accounts = null;
		try {
			accounts = accountDAO.findAccounts(true);
		} catch (Exception e) {
			System.out.println("Exception caught on main program: " + e);
		}

		System.out.println("-----");
		System.out.println("Accounts: " + accounts + "\n");
	}

	void demoTheAfterAdvice(AccountDAO accountDAO) {
		this.addAccounts(accountDAO);

		List<Account> accounts = null;
		try {
			accounts = accountDAO.findAccounts(false);
		} catch (Exception e) {
			System.out.println("Exception caught on main program: " + e);
		}

		System.out.println("-----");
		System.out.println("Accounts: " + accounts + "\n");
	}

	void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("My fortune is: " + data);
		System.out.println("Done!");
	}

	void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune(true);

		System.out.println("My fortune is: " + data);
		System.out.println("Done!");
	}
	
	void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune(true);

		System.out.println("My fortune is: " + data);
		System.out.println("Done!");
	}

}
