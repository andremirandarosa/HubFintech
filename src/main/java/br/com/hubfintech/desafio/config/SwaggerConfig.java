package br.com.hubfintech.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuracao do Swagger
 * @author andre
 */
@Configuration
@EnableSwagger2 //Habilita expecificacao do Swagger 2
public class SwaggerConfig{
 
    /***
     * Configuracao do Swagger
     * @return O Docket
     */
    @Bean
    public Docket api(){
        
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant(Config.PATH_API + "/**")) //Somente os Endpoint da API
                    .build()
                    .enable(true)
                    .apiInfo(apiInfo());
    }
    
    /***
     * Configuração das Informacoes da API
     * @return As informacoes da API
     */
    private ApiInfo apiInfo(){
        
        return new ApiInfoBuilder()
                .title("HubFintech REST API")
                .description("API do Desafio HubFintech.")
                .version("1.0")
                .termsOfServiceUrl("https://github.com/andremirandarosa/HubFintech")
                .contact(new Contact("André Luiz Miranda da Rosa", 
                                     "https://www.linkedin.com/in/andremirandarosa/",
                                     "andremirandarosa@gmail.com"))
                .license("MIT License")
                .licenseUrl("https://github.com/andremirandarosa/HubFintech/blob/master/LICENSE")
                .build();
    }
}