package com.sangdaero.walab;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sangdaero.walab.user.application.service.UserService;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserService mUserService;
	
	public AppSecurityConfig(UserService userService) {
		mUserService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*http
			.antMatcher("/**").authorizeRequests()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login();*/
		
		http
        .authorizeRequests(a -> a
        		.antMatchers("/","/error").permitAll()
        		.anyRequest().authenticated()
        )
        .logout(l -> l
        		.logoutSuccessUrl("/").permitAll()
        )
        .csrf().disable()
        .oauth2Login()
        .userInfoEndpoint()
        .oidcUserService(mUserService);
		
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	
	
}
