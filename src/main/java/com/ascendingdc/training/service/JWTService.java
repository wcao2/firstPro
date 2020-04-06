package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private final String SECRET_KEY="sky-ascending";
    private final String ISSUER="com-ascending";
    private final long EXPIRATION_TIME=86400*1000;
    public String generateToken(Employee e){

        //1:define signature algorithm
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //2:define SECRET_KEY
        byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey=new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        //3:define claim(plyload)
        Claims claims= Jwts.claims();
        claims.setId(String.valueOf(e.getId()));
        claims.setSubject(e.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME));

        //combine those three things above
        JwtBuilder builder=Jwts.builder().setClaims(claims).signWith(signatureAlgorithm,signingKey);
        //4 generate JWT
        return builder.compact();
    }

    public void decyptToken(String token){
        //This line will throw an exception if it is not a signed JWS (as expected)
//        Claims claims = Jwts.parser()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
//                .parseClaimsJws(jwt).getBody();
//        return claims;
    }
}
