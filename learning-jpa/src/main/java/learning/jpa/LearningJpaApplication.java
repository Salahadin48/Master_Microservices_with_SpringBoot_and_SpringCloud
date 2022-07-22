package learning.jpa;

import learning.jpa.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningJpaApplication.class, args);
    }
}
