package com.trainingmug.ecommerce.apigatewayservice.config;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class AuthorizationConfig {

    public Map<String, List<String>> routeRoles() {

        return Map.of(

                "GET:/products",
                List.of(
                        "ROLE_ADMIN",
                        "ROLE_CUSTOMER"
                ),

                "POST:/products",
                List.of(
                        "ROLE_ADMIN"
                ),

                "PUT:/products",
                List.of(
                        "ROLE_ADMIN"
                ),

                "PATCH:/products",
                List.of(
                        "ROLE_ADMIN"
                ),

                "DELETE:/products",
                List.of(
                        "ROLE_ADMIN"
                ),

                "GET:/users",
                List.of(
                        "ROLE_ADMIN",
                        "ROLE_CUSTOMER"
                ),

                "POST:/users",
                List.of(
                        "ROLE_ADMIN"
                ),

                "PUT:/users",
                List.of(
                        "ROLE_ADMIN"
                ),

                "DELETE:/users",
                List.of(
                        "ROLE_ADMIN"
                )
        );
    }
}
