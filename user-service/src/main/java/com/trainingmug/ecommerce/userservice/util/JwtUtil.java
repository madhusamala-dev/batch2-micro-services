package com.trainingmug.ecommerce.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.trainingmug.ecommerce.userservice.entity.UserReponseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Data
@Slf4j
@Component
public class JwtUtil {

    private static final String ROLE_TAG = "role";
    private static final String ISSUED_DATE_TAG = "issued-date";
    private static final String TOKEN_ISSUER = "TrainingMug";

    @Value("${jwt.validity.accessToken}")
    private Long ACCESS_TOKEN_VALIDITY_DURATION;
    @Value("${jwt.validity.refreshToken}")
    private Long REFRESH_TOKEN_VALIDITY_DURATION;
    @Value("${jwt.secret}")
    private String SECRET;

    public String generateAccessToken(UserReponseDto user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8));
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_DURATION))
                .withIssuer(TOKEN_ISSUER)
                .withClaim(ISSUED_DATE_TAG, new Date())
                .withClaim(ROLE_TAG, user.getAuthorities().stream().map(Object::toString).toList())
                .sign(algorithm);
    }

    public String generateRefreshToken(UserReponseDto user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes(StandardCharsets.UTF_8));
        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_DURATION))
                .withIssuer(TOKEN_ISSUER)
                .withClaim(ISSUED_DATE_TAG, new Date())
                .withClaim(ROLE_TAG, user.getAuthorities().stream().map(Object::toString).toList())
                .sign(algorithm);
    }

}
