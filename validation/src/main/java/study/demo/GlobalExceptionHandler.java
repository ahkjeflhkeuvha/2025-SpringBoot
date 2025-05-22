package study.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice("com.example")
public class GlobalExceptionHandler {
    @ExceptionHandler(value=ArithmeticException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> globalArithmeticExceptionHandler(Exception ex) {
        System.out.println("Global arithmetic error handler");







        
        Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("message", ex.getMessage());

        return error;
    }

    @ExceptionHandler(value=Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> globalExceptionHandler(HttpServletRequest request, Exception ex) throws Exception {
        System.out.println("from Global error handler");

        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("message", ex.getMessage());

        return error;
    }
}
