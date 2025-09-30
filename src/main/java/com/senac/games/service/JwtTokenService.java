package com.senac.games.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
  private static final String SECRET_KEY = "fowd3245oh3jkb43li44jig23ij";
  private static final String ISSUER = "gamesolimpiadas-api";

  public String generateToken(UsuarioDetailsImpl usuario) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
      return JWT.create()
          .withIssuer(ISSUER)
          .withIssuedAt(creationDate())
          .withExpiresAt(expirationDate())
          .withSubject(usuario.getUsername())
          .sign(algorithm);
    } catch (JWTCreationException exception) {
      throw new JWTCreationException("Erro ao gerar o token", exception);
    }
  }

  private Instant creationDate(){
    return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
  }
  private Instant expirationDate() {
    return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
  }

  public String getSubjectFromToken(String token) {
    try {
      // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
      return JWT.require(algorithm)
          .withIssuer(ISSUER) // Define o emissor do token
          .build()
          .verify(token) // Verifica a validade do token
          .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
    } catch (JWTVerificationException exception){
      throw new JWTVerificationException("Token inválido ou expirado.");
    }
  }
}
