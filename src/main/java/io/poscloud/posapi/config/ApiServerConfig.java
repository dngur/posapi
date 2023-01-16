package io.poscloud.posapi.config;

import io.poscloud.posapi.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApiServerConfig implements WebMvcConfigurer {

    private static final List<String> API_URI_PATTERNS = List.of("/v1/*");
    //private static final List<String> UPLOAD_URI_PATTERNS = List.of("/v1/handterminal/*");

    CommonInterceptor commonInterceptor;

    @Autowired
    public ApiServerConfig(CommonInterceptor commonInterceptor) {
        this.commonInterceptor = commonInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor).addPathPatterns(API_URI_PATTERNS);
    }
}
