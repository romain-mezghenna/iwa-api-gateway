package com.iwa.api_gateway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class GatewayConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayConfig.class);

    /**
     * Configure the routes of the gateway
     *
     * @param builder RouteLocatorBuilder object to build the routes
     * @return RouteLocator object containing the routes
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        LOGGER.info("Configuring routes");

        return builder.routes()
                .route("user-route", r -> r
                        .path("/api/v1/users/**") 
                        .filters(f -> f.stripPrefix(2) 
                        .filter((exchange, chain) -> {
                                LOGGER.info("Routing to user-service: {}",exchange.getRequest().getPath());
                                return chain.filter(exchange);
                        }))
                        .uri("lb://user-service") 
                )
                .route("auth-route", r -> r
                        .path("/api/v1/auth/**") 
                        .filters(f -> f.stripPrefix(2) 
                        .filter((exchange, chain) -> {
                                LOGGER.info("Routing to user-service: {}",exchange.getRequest().getPath());
                                return chain.filter(exchange);
                        }))
                        .uri("lb://user-service") 
                )
                .route("notification-route", r -> r
                        .path("/api/v1/notifications/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to notification-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://notification-service")
                )
                .route("article-route", r -> r
                        .path("/api/v1/articles/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to article-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://article-service")
                )
                .route("emplacement-route", r -> r
                        .path("/api/v1/emplacements/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to emplacement-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://emplacement-service")
                )
                .route("avis-route", r -> r
                        .path("/api/v1/avis/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to emplacement-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://emplacement-service")
                )
                .route("favoris-route", r -> r
                        .path("/api/v1/favoris/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to emplacement-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://emplacement-service")
                )
                .route("reservation-route", r -> r
                        .path("/api/v1/reservations/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to reservation-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://reservation-service")
                )
                .route("messaging-route", r -> r
                        .path("/api/v1/messaging/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                    LOGGER.info("Routing to messaging-service: {}",exchange.getRequest().getPath());
                                    return chain.filter(exchange);
                                }))
                        .uri("lb://messaging-service")
                )
                .route("image-route", r -> r
                        .path("/api/v1/images/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter((exchange, chain) -> {
                                        LOGGER.info("Routing to image-service: {}",exchange.getRequest().getPath());
                                        return chain.filter(exchange);
                                }))
                        .uri("lb://image-service")
                )
                .route("admin-deletion-route", r -> r
                        .path("/api/v1/admin/deletion-requests**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("lb://notification-service")
                ).build();
    }
}
