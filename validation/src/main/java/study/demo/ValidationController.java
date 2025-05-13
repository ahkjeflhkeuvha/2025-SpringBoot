package study.demo;

import jakarta.validation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/members")
public class ValidationController {

    @GetMapping("/manual_bean_validation")
    public String memberBeanValidation() {
        // 어떠한 종류의 필드값도 없는 상황이므로 여러 검증 에러가 발생
        Member m = new Member();
    /*
    m.setName("김철수");
    m.setAge(20);
    m.setGender("남성");
    m.setEmail("hello@hello.com");
    */

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // 실제 검증 작업을 수행할 Validator 객체 얻어오기
        Validator validator = factory.getValidator();
        // validate 메서드로 객체의 검증 작업 진행, 결과로 검증 에러가 포함된 집합(Set) 객체를 얻을 수 있음
        Set<ConstraintViolation<Member>> violationSet = validator.validate(m);

        String result = "";
        // 모든 검증 에러 확인하기
        for(ConstraintViolation<Member> v : violationSet) {
            result += String.format("field : %s, value : '%s', message : %s",
                    v.getPropertyPath(), // 필드 이름
                    v.getInvalidValue(), // 위반된 값
                    v.getMessage()) + "\n"; // 위반 시 경고 메시지
        }
        System.out.println(result);

        return result;
    }
}
