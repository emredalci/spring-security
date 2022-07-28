package com.security.jwtdemo.security;

import com.security.jwtdemo.service.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        final String jwt = parseJwt(request);
        if (jwt!= null && jwtUtil.validateJwtToken(jwt)){
            final String username = jwtUtil.getUsernameFromToken(jwt);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request,response);
        }
    }

    private String parseJwt(HttpServletRequest request) {
        final String header = request.getHeader("Authorization");

        if (StringUtils.hasText(header)&& header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
