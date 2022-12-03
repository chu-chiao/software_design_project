package com.demovehiclepro.security.configuration;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.security.jwt.JwtTokenVerifier;
import com.demovehiclepro.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.demovehiclepro.security.model.ApplicationUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Slf4j
public class SecurityConfigurationAdapter {

    private final BaseUserRepository baseUserRepositoryImpl;
    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final Environment env;

    public SecurityConfigurationAdapter(BaseUserRepository baseUserRepositoryImpl,
                                        PasswordEncoder passwordEncoder, SecretKey secretKey, Environment env
                                       ) {
        this.baseUserRepositoryImpl = baseUserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
        this.env = env;
    }

    private Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<BaseUser> baseUser = baseUserRepositoryImpl.findByEmail(username);
        return baseUser.map(ApplicationUser::new);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
       final ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        http
                .csrf()
                .disable()
                .authorizeRequests()
//                                .requestMatchers(DENY_ALL_URLS).denyAll()
//                .requestMatchers(PERMITTED_URLS)
//                .permitAll()
//                .requestMatchers(ADMIN_URLS)
//                .hasRole(UserType.ADMIN.name())
//                .antMatchers(SecurityConstants.DEFAULT)
//                .hasAnyRole(
//                        UserType.ADMIN.name(),
//                        UserType.NGO.name(),
//                        UserType.DONOR.name(),
//                        UserType.OFFICIAL.name()
//                )
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .exceptionHandling()
                .and()
                .addFilter(
                        new JwtUsernameAndPasswordAuthenticationFilter(
                                authenticationManager,
                                env,
                                secretKey,
                                applicationContext
                        )
                )
                .addFilterAfter(
                        new JwtTokenVerifier(
                                secretKey,
                                applicationContext
                        ),
                        JwtUsernameAndPasswordAuthenticationFilter.class
                )
                .logout()
                .disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<ApplicationUser> userOptional = selectApplicationUserByUsername(
                        username
                );
                if (userOptional.isEmpty()) {
                    log.error("APPLICATION USER {} NOT FOUND", username);
                    throw new UsernameNotFoundException(
                            String.format("APPLICATION USER'%s' NOT FOUND", username)
                    );
                } else if (!userOptional.get().isEnabled()) {
                    log.error("APPLICATION USER {} NOT VERIFIED and the user is ", username);
                }
                return userOptional.get();
            }
        });
        return provider;
    }



    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList("http://127.0.0.1:8080")
        );
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE")
        );
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedHeader("*");
        configuration.setAllowedHeaders(
                Arrays.asList("Authorization", "Cache-Control", "Content-Type")
        );
        configuration.addAllowedHeader("Authorization");
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Access-Control-Allow-Origin");
        configuration.addExposedHeader("Access-Control-Allow-Headers");
        configuration.addExposedHeader("ETag");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
