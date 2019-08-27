package com.tgpms.auth.config;

import com.tgpms.auth.security.AuthTokenFilter;
import com.tgpms.auth.security.SkipPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAuthConfiguration extends WebSecurityConfigurerAdapter {


	@Bean
	public AuthTokenFilter authenticationTokenFilterBean() throws Exception{
		List<String> pathsToSkip = Collections.singletonList("/login");
		List<String> processingPath = Arrays.asList("/log/**","/logout");
		SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, processingPath);
		AuthTokenFilter filter = new AuthTokenFilter(matcher);
		filter.setAuthenticationManager(this.authenticationManager());
		return filter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/", "/*.html", "/**/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
				.and().addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.cors().configurationSource(corsConfigurationSource());
		httpSecurity.logout().disable();
		httpSecurity.headers().cacheControl();
	}

	/**
	 * 跨域设置
	 * @return null
	 */
	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

}