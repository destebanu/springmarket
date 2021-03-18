package com.strikethenote.springmarket;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringmarketApplication implements WebMvcConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringmarketApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// El idioma por defecto es el español
		Locale espanol = new Locale("es", "ES");
		localeResolver.setDefaultLocale(espanol);
		return localeResolver;
	}
	
	@Bean
	  public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
	    localeInterceptor.setIgnoreInvalidLocale(true);
	    localeInterceptor.setParamName("lang");
	    return localeInterceptor;
	  }

	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	  }
	
}
