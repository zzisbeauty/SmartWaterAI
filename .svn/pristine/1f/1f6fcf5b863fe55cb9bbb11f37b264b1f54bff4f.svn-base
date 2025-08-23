package com.hanwei.core.config;


import com.hanwei.core.common.api.vo.SwaggerProperties;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version : [v1.0]
 * @description : [swagger2 配置类]
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class Knife4jConfiguration {

    private SwaggerProperties swaggerProperties;

    public Knife4jConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }


    @Bean
    public GroupedOpenApi adminApi() { // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("admin-api") // 分组名称
                .pathsToMatch("/**") // 接口请求路径规则
                .build();
    }

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info() // 基本信息配置
                        .title(swaggerProperties.getTitle()) // 标题
                        .description(swaggerProperties.getDescription()) // 描述Api接口文档的基本信息
                        .version(swaggerProperties.getVersion()) // 版本
                        .termsOfService(swaggerProperties.getTermsOfServiceUrl())
                        // 设置OpenAPI文档的联系信息，包括联系人姓名为"patrick"，邮箱为"patrick@gmail.com"。
                        .contact(new Contact().name(swaggerProperties.getName()).email(swaggerProperties.getEmail()))
                        // 设置OpenAPI文档的许可证信息，包括许可证名称为"Apache 2.0"，许可证URL为"http://springdoc.org"。
                        .license(new License().name("Apache 2.0").url("\"http://springdoc.org\""))
                );
    }
}
