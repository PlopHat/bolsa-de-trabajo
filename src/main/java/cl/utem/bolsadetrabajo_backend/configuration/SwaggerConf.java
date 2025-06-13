package cl.utem.bolsadetrabajo_backend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConf {

  private final String moduleName;
  private final String apiVersion;

  public SwaggerConf(
          @Value("${module-name:Bolsa de trabajo}") String moduleName,
          @Value("${api-version:0.0.1}") String apiVersion) {
    this.moduleName = moduleName;
    this.apiVersion = apiVersion;
  }

  // Swagger conf OpenApi v3 goes here
  @Bean
  public OpenAPI customOpenAPI() {
    final String securitySchemeName = "bearerAuth";
    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));
    return new OpenAPI()
            .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
            .components(
                    new Components()
                            .addSecuritySchemes(securitySchemeName,
                                    new SecurityScheme()
                                            .name(securitySchemeName)
                                            .type(SecurityScheme.Type.HTTP)
                                            .scheme("bearer")
                                            .bearerFormat("JWT")
                            )
            )
            .info(new Info()
                    .title("Bolsas de Trabajo API")
                    .version("1.0.0")
                    .description("API for managing job postings and applications.")
                    .version(apiVersion)
                    .contact(new io.swagger.v3.oas.models.info.Contact()
                            .name("UTEM")
                            .email("ejemplo@utem.cl")
                            .url("https://www.utem.cl/")))
            .addServersItem(new Server()
                    .url("http://localhost:8080")
                    .description("Local server for development"));
  }

}
