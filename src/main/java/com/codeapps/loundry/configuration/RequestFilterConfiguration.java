package com.codeapps.loundry.configuration;

import com.codeapps.loundry.auth.service.AuthService;
import com.codeapps.loundry.utill.AuthUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilterConfiguration extends OncePerRequestFilter {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private AuthService authService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");

        String jwtToken = null;
        String userName = null;
        if (header != null && header.startsWith("Baerer ")){
            jwtToken = header.substring(7);

            try {
                userName = authUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e ) {
                System.out.println("Unable to get jwt token");
            } catch (ExpiredJwtException e ) {
                System.out.println("Jwt token is expired");
            }
        } else {
            System.out.println("Jwt Token does not Start with Baerer");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authService.loadUserByUsername(userName);

            if (authUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null,
                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
