package com.cgm.manager.config;

import java.util.function.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cgm.manager.constants.SwaggerConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())           
          .paths(getsPaths())
          .build()
          .apiInfo(apiInfo())
          .forCodeGeneration(true)
          .tags(new Tag(SwaggerConstants.TAG_PATIENTS, "Patient services", 1),
        		  new Tag(SwaggerConstants.TAG_VISITS, "Visits services", 1));                                  
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("CGM - Mangement")
				.build();
	}

	private Predicate<String> getsPaths() {
		return PathSelectors.regex("/management/api/.*");
	}

}