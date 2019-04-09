package org.softuni.university.config;

import org.softuni.university.web.interceptors.IconTitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationInterceptorConfiguration implements WebMvcConfigurer {

    private final IconTitleInterceptor iconTitleInterceptor;

    @Autowired
    public ApplicationInterceptorConfiguration(IconTitleInterceptor iconTitleInterceptor) {
        this.iconTitleInterceptor = iconTitleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.iconTitleInterceptor);
    }
}