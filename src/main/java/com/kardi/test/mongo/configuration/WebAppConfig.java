package com.kardi.test.mongo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Arrays;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
        "com.kardi.test.mongo.controllers",
        "com.kardi.test.mongo.services",
        "com.kardi.test.mongo.beans"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("WEB-INF/resources/");
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder(){
        return new ShaPasswordEncoder();
    }

    // -------- VOTERS for custom roles ------------
    @Bean
    public RoleVoter getRoleVoter() {
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        return roleVoter;
    }

    @Bean
    public AuthenticatedVoter getAuthenticatedVoter() {
        return new AuthenticatedVoter();
    }

    @Bean
    public AccessDecisionManager getAccessDecisionManager() {
        return new AffirmativeBased(Arrays.<AccessDecisionVoter>asList(getRoleVoter(), getAuthenticatedVoter()));
    }
}
