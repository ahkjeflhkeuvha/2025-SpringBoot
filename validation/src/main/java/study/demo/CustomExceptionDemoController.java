package study.demo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/custom_exception")
public class CustomExceptionDemoController {
    @GetMapping("/error_no_odd_number1")
    public String noOddNumber1(@RequestParam("num") Integer num) throws NoOddNumberException {
        if((num % 2) != 0) {
            // 쿼리 스트링으로 전달한 num값이 홀수이면 예외 발생
            throw new NoOddNumberException(num);
        }
        return num + "";
    }

    @GetMapping("/error_no_odd_number2")
    public String noOddNumber2(@RequestParam("num") Integer num) throws NoOddNumberException {
        if((num % 2) != 0) {
            // 위와 똑같지만 ResponseStatusException 객체를 이용한 예외 발생
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "홀수 사용은 허용되지 않음. (입력값 : " + num + ")");
        }
        return num + "";
    }
}