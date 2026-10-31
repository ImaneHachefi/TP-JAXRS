package ma.ws.tpjaxrs;

import ma.ws.tpjaxrs.entities.Account;
import ma.ws.tpjaxrs.enumeration.AccountType;
import ma.ws.tpjaxrs.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;

@SpringBootApplication
public class TpJaxrsApplication {
    public static void main(String[] args) {
        SpringApplication.run(TpJaxrsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository){
        return args -> {
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.Epargne));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.Courant));
            accountRepository.save(new Account(null, Math.random()*9000, new Date(), AccountType.Epargne));

            accountRepository.findAll().forEach(c -> {
                System.out.println(c.toString());
            });
        };
    }
}
