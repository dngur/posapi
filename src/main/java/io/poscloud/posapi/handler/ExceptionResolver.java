package io.poscloud.posapi.handler;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(HttpServletRequest request, Exception e) {

        JsonObject json = new JsonObject();
        json.addProperty("code", "500");
        json.addProperty("message", "kaput");
        json.addProperty("uri", request.getRequestURI());

        log.error("RestController Exception : {}", e.getLocalizedMessage());

        return json.toString();
    }
}
