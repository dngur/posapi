package io.poscloud.posapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UploadControllerInterceptor implements HandlerInterceptor {

    HashGenerator hashGenerator;

    @Autowired
    public UploadControllerInterceptor(HashGenerator hashGenerator) {
        this.hashGenerator = hashGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String fileName = request.getHeader("file-name");

        if (null == fileName || fileName.length() < 30) {
            log.error("invalid file-name.");
            return false;
        }

        String hmacSha256 = hashGenerator.getHmacSHA256(fileName, "cfe9bf4c13174ff788cbc56d9690f556");
        String hash = request.getHeader("hash");

        if (null == hash || !hash.equals(hmacSha256)) {
            log.error("invalid hash.");
            return false;
        }

        return true;
    }
}
