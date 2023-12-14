package com.luv2code.aopdemo;
import java.util.*;
import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO mdao) {
        return runner -> {
//            demoTheBeforeAdvice(theAccountDAO, mdao);
            demoTheAfterReturningAdvice(theAccountDAO);
        };
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

        List<Account> theAccounts=theAccountDAO.findAccounts();
        //Display the accounts
        System.out.println("\n\n Main program: demoAfterReturningAdvice");
        System.out.println("------");
        System.out.println(theAccounts);

    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO mdao) {
        theAccountDAO.addAccount();
        mdao.goToSleep("Hey bro, I am sleeping");

        //call the accountDAO getter/setter methods

        theAccountDAO.setName("Nitin");
        theAccountDAO.setServiceCode("12345");
        theAccountDAO.getName();
        theAccountDAO.getServiceCode();

    }
}
