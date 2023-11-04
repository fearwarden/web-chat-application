package com.cybertaur.webchatapplication.security.interceptors;

import com.cybertaur.webchatapplication.authentication.services.JwtService;
import com.cybertaur.webchatapplication.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        HttpHeaders headers = request.getHeaders();
        if (headers.containsKey("Cookie")) {
            List<String> cookies = headers.get(HttpHeaders.COOKIE);
            if (cookies == null) {
                return false;
            }
            String cookieHeader = cookies.get(0);
            String[] cookiePairs = cookieHeader.split("; ");
            for (String cookie : cookiePairs) {
                String[] cookieParts = cookie.split("=");
                if ("accessToken".equals(cookieParts[0])) {
                    String accessToken = cookieParts.length > 1 ? cookieParts[1] : null;
                    
                    // Check if token expired.
                    try {
                        this.jwtService.isTokenExpired(accessToken);
                    } catch (Exception e) {
                        response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                        return false;
                    }

                    String userEmail = this.jwtService.extractUsername(accessToken);
                    if (userEmail.isEmpty()) {
                        return false;
                    }
                    UserDetails user = this.userService.userDetailsService().loadUserByUsername(userEmail);
                    return this.jwtService.isTokenValid(accessToken, user);
                }
            }
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
