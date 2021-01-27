package com.lucifer.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by lijc on 15/8/27.
 */
@Configuration
@EnableSwagger2
@OpenAPIDefinition(
        info = @Info(
                title = "全局注解描述",
                version = "1.0",
                description = "assess 全局注解描述"
        ),
        externalDocs = @ExternalDocumentation(description = "参考文档",
                url = "https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations"
        )
)
public class SwaggerConfig {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("info Swagger API")
//                .description("Use the api to test")
//                .termsOfServiceUrl("")
//                .version("2.9.2")
//                .build();
//    }
}
