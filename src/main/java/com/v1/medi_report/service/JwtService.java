package com.v1.medi_report.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String secretKey = "fbbdba457d1279560cd2348f4b92c0fbce1b72c908d3fe4a7270f49938fa5ad3";

	public String generateToken(String username) {

		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder().claims().add(claims).subject(username).issuer("mahesh")
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).and().signWith(generateKey())
				.compact();

	}

	private SecretKey generateKey() {

		byte[] decode = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(decode);
	}

	public String extractUsername(String token) {

		return extractClaims(token, Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {

		Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {

		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {

		return extractClaims(token, Claims::getExpiration).before(new Date());
	}
}
