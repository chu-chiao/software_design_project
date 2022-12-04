package com.demovehiclepro.security.configuration;

import com.demovehiclepro.data.enums.UserType;
import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.security.jwt.JwtTokenVerifier;
import com.demovehiclepro.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.demovehiclepro.security.model.ApplicationUser;
import com.demovehiclepro.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import javax.crypto.SecretKey;
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
    private final Environment env;

    @Autowired
    @Lazy AuthenticationManager authenticationManager;

    @Autowired
    public SecurityConfigurationAdapter(BaseUserRepository baseUserRepositoryImpl,
                                        PasswordEncoder passwordEncoder, Environment env
                                       ) {
        this.baseUserRepositoryImpl = baseUserRepositoryImpl;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    private static final RequestMatcher DEALER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(SecurityConstants.ADD_VEHICLE, "POST")
    );

    private static final RequestMatcher OPEN_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(SecurityConstants.REGISTER, "POST")
    );

    private static final RequestMatcher SALES_EXECUTIVE_AND_DEALER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(SecurityConstants.GENERATE_REPORT, "POST")
    );

    private static final RequestMatcher SALES_EXECUTIVE_AND_CUSTOMER_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(SecurityConstants.UPDATE_BOOKING, "POST")
            );

    private static final RequestMatcher ALL_USERTYPE_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher(SecurityConstants.GET_VEHICLES, "GET")
    );


    private Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        Optional<BaseUser> baseUser = baseUserRepositoryImpl.findByEmail(username);
        return baseUser.map(ApplicationUser::new);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

       final ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

        http
                .csrf()
                .disable()
                .authorizeRequests()
                .requestMatchers(OPEN_URLS)
                .permitAll()
                .requestMatchers(DEALER_URLS)
                .hasRole(UserType.DEALER.name())
                .requestMatchers(SALES_EXECUTIVE_AND_CUSTOMER_URLS)
                .hasAnyRole(
                        UserType.SALES_EXECUTIVE.name(),
                        UserType.CUSTOMER.name()
                )
                .requestMatchers(SALES_EXECUTIVE_AND_DEALER_URLS)
                .hasAnyRole(
                        UserType.SALES_EXECUTIVE.name(),
                        UserType.DEALER.name()
                )
                .requestMatchers(ALL_USERTYPE_URLS)
                .hasAnyRole(
                        UserType.DEALER.name(),
                        UserType.SALES_EXECUTIVE.name(),
                        UserType.CUSTOMER.name()
                )
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
                                Helper.secretKey,
                                applicationContext
                        )
                )
                .addFilterAfter(
                        new JwtTokenVerifier(
                                Helper.secretKey,
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
