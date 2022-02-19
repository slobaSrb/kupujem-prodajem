package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.exceptions.ApiError;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/v1/users")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/v1/users/*/purchases")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.PUT, "/v1/users/*")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.DELETE, "/v1/users/*")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.GET,  "/v1/users/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/v1/ratings","/v1/comments","/v1/purchases")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.PUT, "/v1/ratings","/v1/comments/*","/v1/purchases/*")
                .hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/v1/ratings","/v1/comments","/v1/purchases")
                .hasRole("Admin")
                .antMatchers(HttpMethod.GET, "/v1/ratings/*", "/v1/ratings","/v1/comments/*", "/v1/comments","/v1/purchases/*", "/v1/purchases")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/v1/ads/*")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.PUT, "/v1/ads/*")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.PUT, "/v1/ads/**")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.DELETE, "/v1/ads/*")
                .hasAnyRole("User", "Admin")
                .antMatchers(HttpMethod.GET, "/v1/ads/*", "/v1/ads")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .authenticationEntryPoint((request, response, e) -> {
                    log.error("Authentication error: {}", e.getMessage());
                    final ApiError errorInfo = ApiError.builder()
                            .code(ApiError.ErrorCode.AUTHENTICATION_FAILED)
                            .resourceType(ApiError.ResourceType.ACCESS)
                            .message("Failed to authorize the user. Bad username and/or password")
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                })
                .and()
                .exceptionHandling()
                .accessDeniedHandler((reqest, response, e) -> {
                    log.error("Authorization error: {}", e.getMessage());
                    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    final String message = "User" + (auth != null ? " '" + auth.getName() + "'" : "")
                            + " attempted to access the protected URL: " + reqest.getRequestURI();
                    final ApiError errorInfo = ApiError.builder()
                            .code(ApiError.ErrorCode.UNAUTHORIZED)
                            .resourceType(ApiError.ResourceType.ACCESS)
                            .message(message)
                            .build();

                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
                });
        //@formatter:on
    }
}
