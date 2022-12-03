package com.demovehiclepro.security.jwt;


import com.demovehiclepro.data.dtos.UsernameAndPasswordAuthenticationDto;
import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.security.model.ApplicationUser;
import com.demovehiclepro.util.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUsernameAndPasswordAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final BaseUserRepository baseUserRepositoryImpl;
    private final Integer tokenExpirationAfterDays = 200000;
    private Environment env;

    public JwtUsernameAndPasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            Environment env,
            SecretKey secretKey,
            ApplicationContext applicationContext
    ) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.env = env;
        this.baseUserRepositoryImpl = applicationContext.getBean(BaseUserRepository.class);
        String LOGIN_PATH = "/api/v1/login";
        setFilterProcessesUrl(LOGIN_PATH);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationDto authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationDto.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    )
            throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        ApplicationUser applicationUser = ((ApplicationUser) authResult.getPrincipal());
        String email = applicationUser.getBaseUser().getEmail();

        String token = Jwts
                .builder()
                .serializeToJsonWith(new JacksonSerializer(objectMapper))
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        java.sql.Date.valueOf(
                                LocalDate.now().plusDays(tokenExpirationAfterDays)
                        )
                )
                .signWith(secretKey)
                .compact();

        prepareResponseAfterSuccessFullLogin(
                response,
                authResult,
                email,
                token,
                applicationUser.getAuthorities()
        );
    }

    private void prepareResponseAfterSuccessFullLogin(
            HttpServletResponse response,
            Authentication authResult,
            String email,
            String token,
            Collection<? extends GrantedAuthority> authorities
    )
            throws IOException {
        log.info("the token {}", token);

        log.info("user authorities ---> {}", authResult.getAuthorities());
        log.info("user email ---> {}", email);
        log.info("user details ---> {}", authResult.getDetails());

        response.setHeader("Content-Type: application/json", "Accept: application/json");

        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("status", response.getStatus());
        BaseUser user = baseUserRepositoryImpl.findByEmail(email).orElse(null);
        responseObject.put(
                "Authorization", token
        );
        responseObject.put("authorities", authorities);
        responseObject.put("user", user);
        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = response.getOutputStream();
        mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(out, Helper.convObjToONode(responseObject));
        out.flush();
        out.close();
    }

}