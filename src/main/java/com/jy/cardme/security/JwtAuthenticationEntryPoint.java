package com.jy.cardme.security;

import com.jy.cardme.components.TokenExceptionCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int exceptionCode = (int) request.getAttribute("exception");
        if(exceptionCode == TokenExceptionCode.WRONG_TYPE_TOKEN){
            response.sendRedirect("/exception/jwt/wrong-type-token");
        }
        else if(exceptionCode == TokenExceptionCode.EXPIRED_TOKEN){
            response.sendRedirect("/exception/jwt/expired-token");
        }
        else if(exceptionCode == TokenExceptionCode.UNSUPPORTED_TOKEN){
            response.sendRedirect("/exception/jwt/unsupported-token");
        }
        else if(exceptionCode == TokenExceptionCode.WRONG_TOKEN){
            response.sendRedirect("/exception/jwt/wrong-token");
        }
        else if(exceptionCode == TokenExceptionCode.NOT_FOUND_TOKEN_HEADER){
            response.sendRedirect("/exception/jwt/not-found-token-header");
        }
        else{
            response.sendRedirect("exception/jwt/unknown-token-exception");
        }
    }
}
