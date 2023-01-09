package io.poscloud.posapi.handler;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NoHandlerFoundExceptionResolver {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public String noHandlerFoundExceptionHandler(HttpServletRequest request, NoHandlerFoundException e) {

        JsonObject json = new JsonObject();
        json.addProperty("code", "404");
        json.addProperty("message", "nada");
        json.addProperty("uri", request.getRequestURI());

        return json.toString();
    }
}
