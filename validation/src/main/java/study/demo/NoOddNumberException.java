package study.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No Odd Number Allowed.")
public class NoOddNumberException extends Exception {
    public NoOddNumberException(int num) {
        super(num + "은 홀수입니다.");
    }
}