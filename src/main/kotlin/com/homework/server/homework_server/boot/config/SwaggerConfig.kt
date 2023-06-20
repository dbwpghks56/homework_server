package com.homework.server.homework_server.boot.config

import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.DocExpansion
import springfox.documentation.swagger.web.UiConfiguration
import springfox.documentation.swagger.web.UiConfigurationBuilder

@Configuration
@RequiredArgsConstructor
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(
            DocumentationType.OAS_30
        ).securityContexts(listOf(securityContext()))
            .useDefaultResponseMessages(true)
            .apiInfo(appInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.homework.server.homework_server"))
            .paths(PathSelectors.any())
            .build()
    }

    @Bean
    fun uiConfiguration() : UiConfiguration {
        return UiConfigurationBuilder.builder()
            .docExpansion(DocExpansion.LIST)
            .build();
    }

    private fun securityContext() : SecurityContext {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build()
    }

    private fun defaultAuth(): List<SecurityReference> {
        val authorizationScope: AuthorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOf(authorizationScope)

        return listOf(SecurityReference("Authorization", authorizationScopes))
    }

    private fun appInfo() : ApiInfo {
        return ApiInfoBuilder()
            .title("oEmbed 과제 Swagger")
            .description(" oEmbed 과제 관련 api 명세입니다.")
            .version("1.0.0+1")
            .build();
    }
}








