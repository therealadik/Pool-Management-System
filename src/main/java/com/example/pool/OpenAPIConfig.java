package com.example.pool;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Bassein System API",
                description = "Shedule", version = "1.0.0",
                contact = @Contact(
                        name = "Ermolaev Vladislav",
                        email = "v@fladix.ru",
                        url = "telegram"
                )
        )
)
public class OpenAPIConfig {

}