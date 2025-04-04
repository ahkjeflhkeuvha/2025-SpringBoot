package com.example.mvc;

import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class MyJsonData {
    private String value1;
    private Integer value2;

    public String getValue1() { return value1; }
    public Integer getValue2() { return value2; }
    public void setValue1(String value1) { this.value1 = value1; }
    public void setValue2(Integer value2) { this.value2 = value2; }

    @Override
    public String toString(){
        return "MyJsonData \n\tvalue1 : " + getValue1() + "\n\tvalue2 : " + getValue2();
    }
}

class InnerObject {
    private String innerValue1;

    // 세터, 게터 추가
    public String getInnerValue1() {return innerValue1;}
    public void setInnerValue1(String innerValue1) {this.innerValue1 = innerValue1;}
}

class JsonDataWithArrayAndInnerObject {
    private List<String> array1; // String 형식의 데이터만 존재
    private List<Object> array2; // 다양한 데이터 형식이 혼재
    private InnerObject inner; // 내부 객체

    // 세터, 게터 추가
    public List<String> getArray1() {return array1;}
    public void setArray1(List<String> array1) {this.array1 = array1;}
    public List<Object> getArray2() {return array2;}
    public void setArray2(List<Object> array2) {this.array2 = array2;}
    public InnerObject getInner() {return inner;}
    public void setInner(InnerObject inner) {this.inner = inner;}
}

@RestController
@RequestMapping("/renew")
public class MyRenewController {
    @GetMapping(value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE) // context-type 추가
    @ResponseBody
    public String echo(@RequestBody byte[] bytes) {
        String bytesToString = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(bytesToString);
        return bytesToString;
    }


    @GetMapping(value = "/hello-html", produces = MediaType.TEXT_HTML_VALUE)
// 반환 코드도 마찬가지로 그냥 성공적으로 메서드에서 값을 반환하면 자동으로 200이 됨
    @ResponseStatus(HttpStatus.OK)
    public String helloHTML() {
        return "<h1>Hello</h1>";
    }

    @GetMapping(value = "/echo-repeat", produces = MediaType.TEXT_PLAIN_VALUE)
// @RequestHeader 어노테이션을 통해서 X-Repeat-Count에 적힌 숫자 정보 가져오고 없으면 1로 초기화
    public String echoRepeat(@RequestParam("word") String word, @RequestHeader(value = "X-Repeat-Count", defaultValue = "1") Integer repeatCount) throws IOException {
        String result = "";
        for(int i=0;i<repeatCount;i++) {
            result += word;
        }
        return result;
    }

    @GetMapping(value = "/dog-image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] dogImage() throws IOException {
        // resources 폴더의 static 폴더에 이미지 있어야 함
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        // 파일의 바이트 데이터 모두 읽어오기
        byte[] bytes = Files.readAllBytes(file.toPath());

        return bytes;
    }

    @GetMapping(value = "/dog-image-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
// 헤더를 직접 조정하고 싶은 경우 ResponseEntity 타입을 반환하도록 설정 가능
// (꺽쇠 안에는 응답 메시지의 바디 데이터에 포함될 타입을 지정)
    public ResponseEntity<byte[]> dogImageFile() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/dog.jpg");
        byte[] bytes = Files.readAllBytes(file.toPath());

        String filename = "dog.jpg";
        // 헤더 값 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    private ArrayList<String> wordList = new ArrayList<>();

    @PostMapping(value = "/words")
    public ResponseEntity<Void> addWordList(@RequestBody String words) {
        String[] wordsList = words.split("\n");

        for(String word : wordsList) wordList.add(word);

        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/words")
    public ResponseEntity<String> getWordDList() {
        HttpHeaders header = new HttpHeaders();
        return new ResponseEntity<>(String.join(",", wordList), header, HttpStatus.OK);
    }

    @PostMapping("/test")
    public String commandObjectTest(@ModelAttribute MyCommendObject myCommandObject) {
        return myCommandObject.toString();
    }


    // consumes : 요청 데이터 타입 / produces : 응답 데이터 타입
    // 생성자 -> 세터로 객체 채움 -> 리턴
    // json을 객체로 (역직렬화), 객체를 json (직렬화)
    @GetMapping(value = "/json-test", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MyJsonData jsonText(@RequestBody MyJsonData myJsonData) {
        System.out.println(myJsonData);
        return myJsonData;
    }

    @GetMapping(value = "/json-test-2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JsonDataWithArrayAndInnerObject jsonTest2(@RequestBody JsonDataWithArrayAndInnerObject jsonDataWithArrayAndInnerObject) {
        for(Object item : jsonDataWithArrayAndInnerObject.getArray2()) {
            if(item != null) System.out.println(item.getClass().getName());
        }

        return jsonDataWithArrayAndInnerObject;
    }

    @PostMapping(value = "/register_class", consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> registerClass(@RequestBody HashMap<String, Object> registerClassForm) {
        System.out.println(registerClassForm);

        // 응답을 위한 JSON 값 생성을 위해서 역시 HashMap 이용
        HashMap<String, Object> responseJSON = new HashMap<>();
        responseJSON.put("result", "success");
        responseJSON.put("isSuccess", true);
        responseJSON.put("credit", 3);
        System.out.println(responseJSON);

        return responseJSON;
    }
}
