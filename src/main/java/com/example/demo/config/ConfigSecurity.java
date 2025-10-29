package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        // Rutas públicas
                        .requestMatchers("/public/**", "/css/**", "/js/**", "/images/**", "/login", "/error").permitAll()

                        // ADMIN: Acceso total
                        .requestMatchers("/AsignarTecnico").hasRole("ADMIN")

                        // TECNICO: Puede crear incidentes y gestionar estado
                        .requestMatchers("/GestionarEstado").hasAnyRole("ADMIN", "TECNICO")

                        // Todos los roles autenticados pueden crear incidentes y ver listas
                        .requestMatchers("/CrearIncidente", "/ListarIncidentes").authenticated()

                        // Página de inicio
                        .requestMatchers("/", "/index").authenticated()

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/ListarIncidentes", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/acceso-denegado")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails superAdmin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails admin = User.withUsername("tecnico")
                .password(passwordEncoder().encode("tecnico123"))
                .roles("TECNICO")
                .build();

        UserDetails persona = User.withUsername("usuario")
                .password(passwordEncoder().encode("usuario123"))
                .roles("USUARIO")
                .build();

        manager.createUser(superAdmin);
        manager.createUser(admin);
        manager.createUser(persona);

        return manager;
    }
}