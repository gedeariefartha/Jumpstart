package com.project.jumpstartbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.jumpstartbackend.oauth.CustomOAuth2UserService;
import com.project.jumpstartbackend.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.project.jumpstartbackend.oauth.OAuth2AuthenticationFailureHandler;
import com.project.jumpstartbackend.oauth.OAuth2AuthenticationSuccessHandler;
import com.project.jumpstartbackend.security.JwtAuthEntryPoint;
import com.project.jumpstartbackend.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig {

	  @Autowired
	  private JwtAuthEntryPoint authEntryPoint;

	  @Autowired
	  private CustomOAuth2UserService customOAuth2UserService;

	  @Autowired
	  private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

	  @Autowired
	  private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

	  @Autowired
	  private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	  @Bean
	  public static PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }

	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http.cors().and().csrf().disable()
	        // Exception Handling
	        .exceptionHandling()
	        .authenticationEntryPoint(authEntryPoint)
	        .and()

	        // Session Management
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()

	        // Authorize Requests
	        .authorizeRequests()
	        .antMatchers("/oauth2/**", "/api/auth/**").permitAll()
	        .antMatchers("/api/store/**").permitAll()
	        .antMatchers("/api/meal/**").permitAll()
	        .antMatchers("/api/product/**").permitAll()
	        .antMatchers("/api/messages/**").permitAll()
	        .antMatchers("/api/store/post-store").hasRole("admin")
	        .antMatchers("/api/users/me").authenticated()
	        .and()

	        // Login
	        .httpBasic()
	        .disable()
	        .formLogin()
	        .disable()

	        .oauth2Login()
	        .authorizationEndpoint()
	        .baseUri("/oauth2/authorize")
	        .authorizationRequestRepository(cookieAuthorizationRequestRepository())
	        .and()

	        .redirectionEndpoint()
	        .baseUri("/oauth2/callback/*")
	        .and()

	        .userInfoEndpoint()
	        .userService(customOAuth2UserService)
	        .and()

	        .successHandler(oAuth2AuthenticationSuccessHandler)
	        .failureHandler(oAuth2AuthenticationFailureHandler);

	    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	  }

	  @Bean
	  public AuthenticationManager authenticationManager(
	      AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	  }

	  @Bean
	  public JwtAuthenticationFilter jwtAuthenticationFilter() {
	    return new JwtAuthenticationFilter();
	  }

	  @Bean
	  public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
	    return new HttpCookieOAuth2AuthorizationRequestRepository();
	  }
}
