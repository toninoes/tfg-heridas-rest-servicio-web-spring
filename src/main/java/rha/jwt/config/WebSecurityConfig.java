package rha.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import rha.jwt.security.JwtAuthenticationEntryPoint;
import rha.jwt.security.JwtAuthorizationTokenFilter;
import rha.jwt.security.JwtTokenUtil;
import rha.jwt.security.service.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(jwtUserDetailsService)
            .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            // No necesitamos CSRF debido a que nuestro token es invulnerable a este tipo de ataques.
            .csrf().disable()

            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

            // no crear sesiones. Nuestro sistema es sin estado.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .authorizeRequests()
            
            // Para H2 Database
            //.antMatchers("/h2-console/**/**").permitAll()

            .antMatchers("/auth/**").permitAll()
            .antMatchers("/ping/**").permitAll()
            //.antMatchers("/api/imagenes/**").permitAll()
            .antMatchers("/activacion/**").permitAll()
            .anyRequest().authenticated();

        JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
        httpSecurity
            .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // Deshabilitar cacheo de páginas
        httpSecurity
            .headers()
            .frameOptions().sameOrigin()  // Requerido para la consola H2
            .cacheControl();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // AuthenticationTokenFilter ignorará las siguientes rutas
        web
            .ignoring()
            .antMatchers(
                HttpMethod.POST,
                authenticationPath
            )

            // permitir peticiones anónimas en los siguientes recursos
            .and()
            .ignoring()
            .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
            )

            // Consola H2 Database (sólo en modo test. no permitir en producción)
            .and()
            .ignoring()
            .antMatchers("/h2-console/**/**");
    }
}
