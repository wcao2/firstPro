package com.ascendingdc.training.service;

import com.ascendingdc.training.model.Employee;
import com.ascendingdc.training.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTService {
    private final String SECRET_KEY="sky-ascending";
    private final String ISSUER="com-ascending";
    private final long EXPIRATION_TIME=86400*1000;

    private Logger logger= LoggerFactory.getLogger(getClass());
    public String generateToken(Employee e){

        //1:define signature algorithm
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //2:define SECRET_KEY
        byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey=new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        //3:define claim
        Claims claims= Jwts.claims();
        claims.setId(String.valueOf(e.getId()));
        claims.setSubject(e.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME));

        List<Role> roles = e.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";
        String allowedResource = roles.stream().map(role -> role.getAllowedResource()).collect(Collectors.joining(","));//why we need this line
        claims.put("allowedResource",  allowedResource);//why we need this line
        for (Role role : roles) {
            if (role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(), allowedReadResources, ",");
            if (role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(), allowedCreateResources, ",");
            if (role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(), allowedUpdateResources, ",");
            if (role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(), allowedDeleteResources, ",");
        }
        claims.put("allowedReadResources", allowedReadResources.replaceAll(".$", ""));//??????????????
        claims.put("allowedCreateResources", allowedCreateResources.replaceAll(".$", ""));
        claims.put("allowedUpdateResources", allowedUpdateResources.replaceAll(".$", ""));
        claims.put("allowedDeleteResources", allowedDeleteResources.replaceAll(".$", ""));

        JwtBuilder builder=Jwts.builder().setClaims(claims).signWith(signatureAlgorithm,signingKey);
        //4 generate JWT
        return builder.compact();
    }

    //写一个test,根据generate token above， parse 这个token
    public Claims decyptToken(String token){
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        logger.debug("Claims: "+claims.toString());
        return claims;
    }
}






