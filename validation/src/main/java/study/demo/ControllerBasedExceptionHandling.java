package study.demo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerBasedExceptionHandling {
    @GetMapping("/error_handle_from_method1")
    public void errorHandleFromMethod(@RequestParam("num") Integer num, HttpServletResponse response) throws Exception {
        try {
            response.setStatus(200);
            response.setHeader("Content-Type", "text/plain; charset=utf-8");
            // 0으로 나누면 에러 발생
            response.getWriter().write("계산 결과 : " + (10 / num));
        } catch(Exception e) {
            // 직접 catch 블록 내부에서 상태 코드값 설정하고 응답 메시지 반환
            response.setStatus(400);
            response.setHeader("Content-Type", "text/plain; charset=utf-8");
            response.getWriter().write("잘못된 파라미터 전달");
        }
    }

    @GetMapping(value="/error_handle_from_method2", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> honestErrorHandling() {
        try {
            boolean error = true;
            // 뭔가를 진행하다가 에러가 났다고 가정
            if(error) throw new Exception("심각한 에러 발생");
        } catch (Exception e) {
            // 직접 catch 블록 내부에서 상태 코드값 설정하고 응답 메시지 반환
            return ResponseEntity.badRequest().body("{ \"status\": 400, \"reason\": \"" + e.getMessage() + "\" }");
        }

        return ResponseEntity.ok().body("{ \"result\": \"success\" }");
    }


}