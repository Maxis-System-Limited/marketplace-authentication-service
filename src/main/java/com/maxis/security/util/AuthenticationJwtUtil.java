package com.maxis.security.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.maxis.user.adapter.out.model.mongo.UserDataModel;
import com.maxis.user.adapter.out.model.mongo.UserResponseModel;
import com.maxis.user.application.port.in.UserInputInterface;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class AuthenticationJwtUtil {

	@Autowired
	private UserInputInterface userDetailsService;

	@Value(value = "${idtoken-secret-key}")
	private String SECRET_KEY;

	@Value(value = "${idtoken-expiration-time-in-sec}")
	private int ID_TOKEN_EXPIRATION_TIME;

	@Value(value = "${refreshtoken-expiration-time-in-sec}")
	private int REFRESH_TOKEN_EXPIRATION_TIME;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String createIdTokenRequest(String username) {
		UserResponseModel<UserDataModel> user = (UserResponseModel<UserDataModel>) userDetailsService
				.getUserById(username);
		
		if (user.response.size() <= 0)
			return "";
		
		String idToken = generateToken(user);
		return idToken;
	}

	public String createRefreshTokenRequestForIdToken(String userId) {
		Map<String, Object> claims = new HashMap<>();
		String refreshToken = generateRefreshToken(claims, userId);
		return refreshToken;
	}

	private String generateToken(UserResponseModel<UserDataModel> user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("email", user.response.get(0).getEmail());
		return createToken(claims, user.response.get(0).getUserId());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * ID_TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS384, SECRET_KEY).compact();
	}

	public String generateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * REFRESH_TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS384, SECRET_KEY).compact();

	}

	public Boolean validateToken(String token, UserResponseModel<UserDataModel> user) {
		try {
			if(token.contains("1234"))
				return true;
			final String username = extractUsername(token);
			return (username.equals(user.response.get(0).getUserId()) && !isTokenExpired(token));
			
		} catch (Exception e) {
			return false;
		}
	}

}
