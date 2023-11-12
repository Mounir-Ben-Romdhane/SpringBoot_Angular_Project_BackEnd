package tn.esprit.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import tn.esprit.spring.DAO.Dto.RegisterRequest;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Services.Etudiant.EtudiantService;

import static tn.esprit.spring.DAO.Entities.Role.ADMIN;
import static tn.esprit.spring.DAO.Entities.Role.USER;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootFirstAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFirstAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            EtudiantService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .nomEt("Admin")
                    .prenomEt("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());

            var manager = RegisterRequest.builder()
                    .nomEt("Etudiant")
                    .prenomEt("Etudiant")
                    .email("Etudiant@mail.com")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("Etudiant token: " + service.register(manager).getToken());

        };
    }
}
