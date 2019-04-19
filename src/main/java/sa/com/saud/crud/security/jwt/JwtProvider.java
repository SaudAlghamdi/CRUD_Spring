package sa.com.saud.crud.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import sa.com.saud.crud.security.service.UserPrinciple;
import saud.com.crud.Constants.SecurityConstants;

@Component
public class JwtProvider {
		
	
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);


    /**
     * @param authentication
     * @return
     */
    public String generateJwtToken(Authentication authentication) {
 
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        logger.info("Befor adding Jwt Expieration Time = "+(new Date(new Date().getTime())));

        logger.info("Jwt Expieration Time = "+(new Date((new Date()).getTime() + SecurityConstants.FIVE_MINUTE_IN_MILLIS)));
        
        logger.info("Jwt Secert (For Testing pupose ) = "+SecurityConstants.JWT_SECERT);
       
        return Jwts.builder()
                    .setSubject((userPrincipal.getUsername()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + SecurityConstants.FIVE_MINUTE_IN_MILLIS))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECERT)
                    .compact();
        
       
    }
    
    /**
     * @param authToken
     * @return
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECERT).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature ");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token ");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token ");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token ");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty ");
        }
        
        return false;
    }
    
    /**
     * @param token
     * @return
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                      .setSigningKey(SecurityConstants.JWT_SECERT)
                      .parseClaimsJws(token)
                      .getBody().getSubject();
    }
	

}
