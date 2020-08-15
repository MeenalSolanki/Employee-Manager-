package com.mindBrowser.manageremployee.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mindBrowser.manageremployee.jwt.AuthEntryPointJwt;
import com.mindBrowser.manageremployee.jwt.AuthTokenFilter;
import com.mindBrowser.manageremployee.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements RepositoryRestConfigurer
{
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	private EntityManager entityManager;
	
	
	WebSecurityConfiguration(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/mindbowser/auth/**").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config)
	{
		exposeIds(config);

}


	//expose id at endpoint so we can get from json / can view id's
	private void exposeIds(RepositoryRestConfiguration config) {
	
			Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
			logger.info(" entityManager.getMetamodel(): "+entityManager.getMetamodel().getEntities());
			
			List<Class> entityClasses = new ArrayList<>();
			
			for(EntityType tempEntityType : entities)
			{
				entityClasses.add(tempEntityType.getJavaType());
				logger.info(" entity: "+tempEntityType.getJavaType());
			}
			
			Class[] domainTypes =entityClasses.toArray(new Class[0]);
			
			logger.info(" domainTypes: "+domainTypes);
			config.exposeIdsFor(domainTypes);
		
	}

}