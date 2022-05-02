package com.example.demo.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("UserDetailProvider")
 	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration=new CorsConfiguration();
      configuration.applyPermitDefaultValues();
      configuration.setExposedHeaders(Arrays.asList("X-AUTH-TOKEN"));
      UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }

	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("CONFIGGGG");
		http.csrf().disable()
        .cors().and()
        .authorizeRequests()
        .antMatchers("/auth/**", "/base/**", "/ang/**", "/angalb/**", "/angpes/**", "/posetilac/**", "/security/**")
        .permitAll()   
        .antMatchers("/administrator/**")
        .hasAnyRole("admin", "ROLE_admin")
        .antMatchers("/polaznik/**")
        .hasAnyRole("polaznik", "ROLE_polaznik")
        .antMatchers("/predavac/**")
        .hasAnyRole("predavac", "ROLE_predavac")
        .and()
        .formLogin()
        .loginPage("/auth/loginPage")
        .loginProcessingUrl("/login")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/base/pocetna")
        .failureUrl("/auth/failure");
	}
	
}
