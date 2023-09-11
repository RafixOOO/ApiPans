package Pans.Api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*"); // Ustaw odpowiednią domenę lub "*", aby zezwolić na wszystkie domeny.
        configuration.addAllowedMethod("*"); // Ustaw metody HTTP, które chcesz zezwolić (np. GET, POST, PUT, DELETE).
        configuration.addAllowedHeader("*"); // Ustaw nagłówki, które chcesz zezwolić.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

