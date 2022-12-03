package com.demovehiclepro.security.jwt;

import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.repository.BaseUserRepository;
import com.demovehiclepro.util.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {
    private final SecretKey secretKey;
    private final BaseUserRepository baseUserRepositoryImpl;

    private final String TOKEN_PREFIX = "Bearer";
    private final String AUTHORIZATION = "Authorization";

    public JwtTokenVerifier(
            SecretKey secretKey,
            ApplicationContext applicationContext
    ) {
        this.secretKey = secretKey;
        this.baseUserRepositoryImpl = applicationContext.getBean(BaseUserRepository.class);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (
                !Helper.notBlank(authorizationHeader) ||
                        !authorizationHeader.startsWith(TOKEN_PREFIX)
        ) {
            log.info("AUTH HEADER : {}", authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(TOKEN_PREFIX, "");

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Jws<Claims> claimsJws = Jwts
                    .parserBuilder()
                    .deserializeJsonWith(new JacksonDeserializer(objectMapper))
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();

            String username = body.getSubject();
            Optional<BaseUser> userOptional = baseUserRepositoryImpl.findByEmail(username);
            if (userOptional.isEmpty()) {
                String s = "The user does not exist " + username;
                log.error(s);
                blockerResponse(response, s, 403);
                return;
            }
            BaseUser user = userOptional.get();
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType().name()));
            log.info(
                    " The authorities is as follows {}",
                    Arrays.toString(authorities.toArray())
            );

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            var msg = "Token %s cannot be trusted";
            blockerResponse(response, String.format(msg, token), 403);
            log.error(msg);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void blockerResponse(HttpServletResponse response, String message, int code)
            throws IOException {
        response.setHeader("Content-Type: application/json", "Accept: application/json");
        response.setStatus(code);
        Map<String, Object> responseObject = new HashMap<>();
        responseObject.put("status", response.getStatus());
        responseObject.put("message", message);

        ObjectMapper mapper = new ObjectMapper();
        OutputStream out = response.getOutputStream();
        mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(out, Helper.convObjToONode(responseObject));
        out.flush();
        out.close();
    }


}
