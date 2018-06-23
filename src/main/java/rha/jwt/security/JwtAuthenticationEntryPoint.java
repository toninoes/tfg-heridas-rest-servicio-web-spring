package rha.jwt.security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        /*
         *  Esto se invoca cuando el usuario intenta acceder a un recurso REST protegido 
         *  sin proporcionar ninguna credencial. Sólo debemos enviar una respuesta 401 
         *  (No autorizado) porque no hay una "página de inicio de sesión" a la cual
         *  redirigir.
         */
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
