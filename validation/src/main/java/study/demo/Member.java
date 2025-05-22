package study.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
class Member implements Serializable {
    @NotBlank
    @Size(min = 2, max = 10)
    private String name;

    @Positive
    @Min(1)
    @Max(130)
    private int age;

    @NotNull
    @NotBlank
    @Email
    private String email;

    // 정규식 패턴과 일치하는 경우 검증 통과
    @Pattern(regexp = "[a-zA-Z0-9]{4,8}", message = "비밀번호 양식이 틀렸습니다.")
    private String password;

    private String gender;

    // 검증용 메소드를 정의한 후 @AssertTrue 혹은 @AssertFalse 어노테이션 사용
    // @AssertTrue : 반환값이 "참"이어야 검증 통과로 가정
    @AssertTrue(message = "성별은 여성이거나 남성이어야 합니다.")
    @JsonIgnore
    public boolean isValidGender() {
        // false 반환하여 검증 실패
        if (gender == null) return false;

        // 검증을 통과한 경우 true 반환
        return gender.equals("남성") || gender.equals("여성");
    }

    private ArrayList<String> hobbies = new ArrayList<String>();

    private ArrayList<String> interests = new ArrayList<String>();
}