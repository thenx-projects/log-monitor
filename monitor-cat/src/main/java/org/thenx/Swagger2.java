package org.thenx;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
//    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等

    @Bean
    public Docket demoApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization").description("input the token for authentication either in the authorization field or in the token field").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        ParameterBuilder aParameterBuilder1 = new ParameterBuilder();
        aParameterBuilder1.name("token").description("input the token for authentication either in the authorization field or in the token field").modelRef(new ModelRef("string")).parameterType("query").required(false).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        aParameters.add(aParameterBuilder1.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).useDefaultResponseMessages(false).globalOperationParameters(aParameters)
                .select().apis(RequestHandlerSelectors.basePackage("com.tgpms.web")).build();
    }

    protected ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("jeff", "", ""))
                //版本号
                .version("1.0")
                //描述
                .description("现场施工管理Web Api")
                .build();
    }
}
