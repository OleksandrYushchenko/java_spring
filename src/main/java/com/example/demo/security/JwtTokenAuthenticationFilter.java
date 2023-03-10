package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService myUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String token = header.split(" ")[1].trim();
        // On “parse” le token en utilisant la même clé de signature qui sera
        //utilisée pour générer le token à l’authentification (“secret”)
        final Claims claims =
                Jwts.parser().setSigningKey("secret".getBytes()).parseClaimsJws(token)
                        .getBody();
        // On récupère le nom de l’utilisateur indiqué dans l’objet
        final String username = claims.getSubject();
        // On récupère les informations de l’utilisateur grâce au repository
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(username
        );
        final UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );
        // Ajoute les informations de l’utilisateur
        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );
        // Met à jour le contexte d’authentification
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Important : permet à Spring de continuer le traitement !
        filterChain.doFilter(request, response);
    }
}
