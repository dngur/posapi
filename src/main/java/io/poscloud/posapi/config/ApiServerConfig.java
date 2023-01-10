package io.poscloud.posapi.config;

import io.poscloud.posapi.interceptor.ApiAuthInterceptor;
import io.poscloud.posapi.interceptor.UploadControllerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ApiServerConfig implements WebMvcConfigurer {

    private static final List<String> API_AUTH_URI_PATTERNS = List.of("/v1/auth/*");
    private static final List<String> UPLOAD_URI_PATTERNS = List.of("/v1/handterminal/*");

    ApiAuthInterceptor apiAuthInterceptor;
    UploadControllerInterceptor uploadControllerInterceptor;

    @Autowired
    public ApiServerConfig(ApiAuthInterceptor apiAuthInterceptor, UploadControllerInterceptor uploadControllerInterceptor) {
        this.apiAuthInterceptor = apiAuthInterceptor;
        this.uploadControllerInterceptor = uploadControllerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiAuthInterceptor).addPathPatterns(API_AUTH_URI_PATTERNS);
        registry.addInterceptor(uploadControllerInterceptor).addPathPatterns(UPLOAD_URI_PATTERNS);
    }
}
