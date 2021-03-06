package io.github.hobbstech.sara_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Profile(value = {"dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String projectName;

    @Bean
    public Docket api() {

        List<ResponseMessage> list = new java.util.ArrayList<>();
        list.add(new ResponseMessageBuilder().code(500).message("500 message")
                .responseModel(new ModelRef("Result")).build());
        list.add(new ResponseMessageBuilder().code(401).message("Unauthorized")
                .responseModel(new ModelRef("Result")).build());
        list.add(new ResponseMessageBuilder().code(406).message("Not Acceptable")
                .responseModel(new ModelRef("Result")).build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("io.github.hobbstech"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false).apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, list)
                .globalResponseMessage(RequestMethod.POST, list);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(projectName).description("")
                .termsOfServiceUrl("http://www.hobbstech.github.io/")
                .contact(new Contact("Wilson Chiviti", "http://www.hobbstech.github.io", "wilsonchiviti@gmail.com"))
                .license("Open Source").licenseUrl("http://www.hobbstech.github.io").version("1.0.0").build();
    }

}
