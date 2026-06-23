package com.example.bankmanagementsystem.security;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {
private final CustomUserDetailsService userDetailsService;
public SecurityConfig(CustomUserDetailsService userDetailsService){
    this.userDetailsService = userDetailsService;
}
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/transactions/statement/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        )
                        .permitAll()

                        .requestMatchers(
                                "/api/users/count",
                                "/api/accounts/count",
                                "/api/transactions/count",
                                "/api/loans/count",
                                "/api/beneficiaries/count"
                        )
                        .hasAnyRole("USER","BANK","ADMIN")

                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        .requestMatchers("/api/loan-requests/**")
                        .hasRole("BANK")

                        .requestMatchers("/api/accounts/**",
                                "/api/transactions/**")
                        .hasAnyRole("USER","BANK","ADMIN")

                        .anyRequest().authenticated())

                .addFilterBefore(
                        new JwtAuthenticationFilter(userDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}