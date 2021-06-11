package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("info.app")
@Getter
@Setter
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
//@ConditionalOnExpression("${springdoc.swagger-ui.enabled:true} and '${spring.profile.active}'.equalsIgnoreCase('DEV')")
public class OpenApiConfig {

    private String title;
    private String description;
    private String version;
    private String termOfService;
    private String contactName;
    private String contactEmail;
    private String contactUrl;
    private String licenseName;
    private String licenseUrl;

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(info());
    }

    private Info info() {
        return new Info()
            .title(title)
            .description(description)
            .version(version)
            .contact(new Contact().name(contactName).email(contactEmail).url(contactUrl))
            .license(new License().name(licenseName).url(licenseUrl));
    }

}
