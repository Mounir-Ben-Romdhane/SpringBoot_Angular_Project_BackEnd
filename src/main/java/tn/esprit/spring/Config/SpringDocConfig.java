package tn.esprit.spring.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("4TWIN6-Swagger")
                .description("TP étude de cas")
                .contact(contactAPI());
    }

    public Contact contactAPI() {
        Contact contact = new Contact().name("4TWIN6")
                .email("esprit2324-4twin6@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }

    /*
    @Bean
    public GroupedOpenApi productPublicApi() {
        return GroupedOpenApi.builder()
                .group("Only Product Management API")
                .pathsToMatch("/chamber/**")
                .pathsToExclude("**")
                .build();
    }
     */
}
