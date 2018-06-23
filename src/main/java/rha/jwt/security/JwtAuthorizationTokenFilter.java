package rha.jwt.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private String tokenHeader;

    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        logger.debug("procesando autenticación para '{}'", request.getRequestURL());

        final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("Se produjo un error al obtener el nombre de usuario de token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("el token ha expirado y ya no es válido", e);
            }
        } else {
            logger.warn("no se pudo encontrar la cadena portadora (Bearer), se ignorará el encabezado");
        }

        logger.debug("comprobación de autenticación para el usuario '{}'", username);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            logger.debug("el contexto de seguridad era nulo, así que autorizando al usuario");

            /*
             * No es obligatorio cargar los detalles de uso de la base de datos. También puede 
             * almacenar la información en el token y leerlo desde allí.
             */
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            /*
             * Por simple validación es suficiente simplemente verificar la integridad del token.
             * No es necesario que llamar a la BBDD
             */
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                logger.info("autorizado usuario '{}', estableciendo el contexto de seguridad", username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
