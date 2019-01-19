package com.boon.boonapp.config;

import com.boon.boonapp.interceptor.AuthHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.boon.boonapp.controller.BoonServiceConstants.PATHS_OUT_OF_SEC;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    private final AuthHeaderInterceptor authHeaderInterceptor;

    @Autowired
    public MvcConfiguration(AuthHeaderInterceptor authHeaderInterceptor) {
        this.authHeaderInterceptor = authHeaderInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHeaderInterceptor)
                .excludePathPatterns(PATHS_OUT_OF_SEC);
    }
}
