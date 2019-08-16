package com.utility;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.smartsense.exception.JWTException;
//import com.smartsense.utility.constant.ApplicationConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author vishal
 *
 */
public class JWTUtils {
	
	private JWTUtils(){}

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

	/**
	 * create jwt on request
	 * 
	 * @param id
	 * @param issuer
	 * @param subject
	 * @param ttlMillis
	 * @return
	 */
	public static String createJWT(String id, String issuer, String subject,int ttlMinute) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		LOGGER.debug("Before expiration time ="+cal.getTime());
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("AESPL0899Q34343434");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer).signWith(signatureAlgorithm, signingKey);
		if (ttlMinute >= 0) {
			cal.add(Calendar.MINUTE, ttlMinute);
			LOGGER.debug("After expiration time ="+cal.getTime());
			builder.setExpiration(cal.getTime());
		}
		return builder.compact();
	}

	
	/**
	 * Generate JWT token verification status map.
	 * @param jwt
	 * @return Verification map
	 * @throws JWTException
	 */
	public static Map<String,Object> verifyJWT(String jwt) {
		final String method = " verifyJWT() ";
		boolean isTokenExpired = false;
		Map<String, Object> map = new HashMap<>();
	 	try {
	 		System.out.println(method+" verifyJWT : Enter");
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("AESPL0899Q34343434")).parseClaimsJws(jwt).getBody();
			System.out.println(method+"ID: " + claims.getId());
			System.out.println(method+"Subject: " + claims.getSubject());
			System.out.println(method+"Issuer: " + claims.getIssuer());
			System.out.println(method+"Expiration: " + claims.getExpiration());
			map.put(ApplicationConstant.STRING_JWT_SUBJECT, claims.getSubject());
			if(claims.getExpiration().getTime()<System.currentTimeMillis()){
				System.out.println("Token expired");
				isTokenExpired = true;
			}
			System.out.println(method+" : Exit : isTokenExpired : "+isTokenExpired);
		} catch(ExpiredJwtException ex){
			LOGGER.error(method+" : JWT Token Expired :"+ex,ex);
			//throw new JWTException("JWT Token Expired : "+ex.getMessage());
		} catch (Exception e) {
			LOGGER.error(method+" : Exception :",e);
			//throw new JWTException(method+" : Exception :"+e.getMessage());
		} 
	 	map.put(ApplicationConstant.STRING_IS_TOKEN_EXPIRED, isTokenExpired);
		return map;
	}

	/**
	 * parse request object and return jwt token
	 * @param httpRequest
	 * @return Authorization token
	 * @throws ServletException
	 */
	public static String getToken(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse) {
		final String method = " getToken() ";
		System.out.println(method+" : start");
		String token = null;
		final String authorizationHeader = httpRequest.getHeader("authorization");
		if (authorizationHeader == null) {
			System.out.println(method+" authorizationHeader is null");
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			//throw new JWTException("Unauthorized: No Authorization header was found");
		}
		String[] parts = authorizationHeader.split(" ");
		if (parts.length != 2) {
			System.out.println(method+" authorizationHeader not in valid formate");
			httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			//throw new JWTException("Unauthorized: Format is Authorization: Bearer [token]");
		}

		String scheme = parts[0];
		String credentials = parts[1];

		Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
		if (pattern.matcher(scheme).matches()) {
			token = credentials;
		}
		LOGGER.debug(method+" : exit ");
		return token;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getRandomUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	
	/**
	 * Return unique random number with given size.
	 * @param size
	 * @return
	 */
	public static String getRandomUUID(int size){
		UUID uuid = UUID.randomUUID();
		if(size>uuid.toString().length()){
			return uuid.toString();
		}else{
			return uuid.toString().substring(0,size);
		}
	}
	
	
	/**
	 * Return public token verification status map.
	 * @param token
	 * @return Map
	 * @throws JWTException
	 */
	public static Map<String,Object> verifyPublicToken(String token) {
		boolean isTokenValid = false;
		Map<String, Object> map = new HashMap<>();			
		//if(token.equals(ApplicationConstant.DEFAULT_TOKEN))
		if(token.equals("AESPL0899Q"))
			isTokenValid = true;
		map.put(ApplicationConstant.STRING_IS_TOKEN_EXPIRED, !isTokenValid);
		return map;
	}
}
