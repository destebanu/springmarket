package com.strikethenote.springmarket.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

    @Autowired
    private UserDetailsService userDetailsService;
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(
                            "/",
                            "/volver",
                            "/index",
                            "/js/**",
                            "/css/**",
                            "/favicon.ico",
                            "/pictures/**",
                            "/usuario/**",
		                    "/product/productsearch",
		                    "/product/results",
		                    "/product/productid/{idProducto}",
		                    "/imagenes/**").permitAll()
                    .antMatchers("/compra/**").hasAnyAuthority("registrado","admin")	
                    .antMatchers("/product/**").hasAuthority("admin")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/usuario/login")
                    .loginProcessingUrl("/login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .permitAll()
                    .failureUrl("/usuario/login_error")
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
                http.csrf().ignoringAntMatchers("/pictures/**");
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImpl();
    }
    
}