package io.poscloud.posapi.config;

import io.poscloud.posapi.interceptor.ApiAuthInterceptor;
import io.poscloud.posapi.interceptor.HandTerminalControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApiServerConfig implements WebMvcConfigurer {

    private static final List<String> API_AUTH_URI_PATTERNS = List.of("/v1/auth/*");
    private static final List<String> HAND_TERMINAL_URI_PATTERNS = List.of("/v1/handterminal/*");

    ApiAuthInterceptor apiAuthInterceptor;
    HandTerminalControllerInterceptor handTerminalControllerInterceptor;

    @Autowired
    public ApiServerConfig(ApiAuthInterceptor apiAuthInterceptor, HandTerminalControllerInterceptor handTerminalControllerInterceptor) {
        this.apiAuthInterceptor = apiAuthInterceptor;
        this.handTerminalControllerInterceptor = handTerminalControllerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiAuthInterceptor).addPathPatterns(API_AUTH_URI_PATTERNS);
        registry.addInterceptor(handTerminalControllerInterceptor).addPathPatterns(HAND_TERMINAL_URI_PATTERNS);
    }
}
