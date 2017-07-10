package com.ref.arch.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	  @Override
	    public void configure(HttpSecurity http) throws Exception {
	        http
	                .csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/**").authenticated()
	                .antMatchers(HttpMethod.GET, "/sample").hasAuthority("amit_read");
	    }

	  @Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	        resources.resourceId("sample").tokenStore(tokenStore);
	    }

	    @Autowired
	    TokenStore tokenStore;

	    @Autowired
	    JwtAccessTokenConverter tokenConverter;
}
