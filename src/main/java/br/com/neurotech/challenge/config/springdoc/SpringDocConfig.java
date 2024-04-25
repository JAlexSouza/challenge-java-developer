package br.com.neurotech.challenge.config.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Credit Analysis API")
                        .description("Credit verifying and applicability for clients")
                        .version("0.0.1-SNAPSHOT"))
                .externalDocs(new ExternalDocumentation()
                        .description("Main repository")
                        .url("https://github.com/Neurolake/challenge-java-developer/tree/main"));
    }
}
