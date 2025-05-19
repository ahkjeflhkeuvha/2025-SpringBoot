package com.example.mvc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Calculate {
    int num1;
    int num2;
    String op;
    int result;

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum1() {
        return this.num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum2() {
        return this.num2;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getOp() {
        return this.op;
    }

    public void setResult() {
        if(this.op.equals("+")) {
            this.result = this.num1 + this.num2;
        } else if(this.op.equals("-")) {
            this.result = this.num1 - this.num2;
        } else if(this.op.equals("*")) {
            this.result = this.num1 * this.num2;
        } else if(this.op.equals("/")) {
            this.result = this.num1 / this.num2;
        } else {
            this.result = 0;
        }
    }

    public int getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        return "calculator {\n\tnum1 : " + this.getNum1() + "\n\tnum2 : " + this.getNum2() + "\n\top : " + this.getOp() + "\n\tresult : " + this.getResult() + "\n}";
    }
}

class EmailAddress {
    ArrayList<String> email_address = new ArrayList<String>();

    public void setEmail_address(String email_address) {
        this.email_address.add(email_address);
    }

    public ArrayList<String> getEmail_address() {
        return this.email_address;
    }

}

class SevenJson {
    String world;
    String from;
    String to;
    int importance;

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}

class EightJson {
    String title;
    String author;
    String to;
    float rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}

class EightResult {
    String result;
    String id;
    boolean success;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

class Vote {
    public String option;
    public int count;

    public Vote(String option) {
        this.option = option;
        this.count = 0;
    }

    public void vote() {
        this.count += 1;
    }
}

class WiseSaying {
    private String quote;

    public void setQuote(String quote){
        this.quote = quote;
    }

    public String getQuote(){
        return this.quote;
    }
}

class OptionClass {
    private String result;
    private ArrayList<Vote> votes;

    public OptionClass(){
        this.result = "result";
        this.votes = new ArrayList<>();
    }

    public void addVote(Vote vote) {
        this.votes.add(vote);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<Vote> getVotes() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "OptionClass{" +
                "result='" + result + '\'' +
                ", votes=" + votes +
                '}';
    }
}

class BookInfo {
    private String title;
    private String author;
    private int first_realese;
    private float rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFirst_realese() {
        return first_realese;
    }

    public void setFirst_realese(int first_realese) {
        this.first_realese = first_realese;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", first_realese=" + first_realese +
                ", rating=" + rating +
                '}';
    }
}

class BookInfoResult {
    private String result;
    private String id;
    private boolean success;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}

class VoteCreationDTO {
    private String option;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

@RestController
@RequestMapping("/work")
public class HomeworkController {
    @GetMapping("/get_test")
    @ResponseBody
    public String getPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @PostMapping("/post_test")
    @ResponseBody
    public String postPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    private String message = "HelloWOrld";

    @PatchMapping("/update_message")
    @ResponseBody
    public String updateMessage(@RequestParam("message") String newMessage, @RequestBody String newBodyMessage) {
        message = newMessage;
        message = newBodyMessage;
        return newMessage;
    }

    @PatchMapping("/update_message_path/{message}")
    @ResponseBody
    public String updateMessagePath(@PathVariable("message") String newMessage) {
        message = newMessage;

        return message;
    }

    @PostMapping(value = "/calc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Calculate> calculate(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("op") String op) {
        Calculate cal = new Calculate();
        cal.setNum1(num1);
        cal.setNum2(num2);
        cal.setOp(op);
        cal.setResult();

        System.out.println(cal);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(cal, headers, HttpStatus.OK);
    }

    private int visitCount = 0;
    @PostMapping(value = "/update_visit_count", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String visitCount(@RequestBody String data) {
        visitCount += 1;
        return String.valueOf(visitCount);
    }

    @PostMapping(value = "/update_visit_count_json")
    @ResponseBody
    public HashMap<String, Integer> getJsonVisitCount(@RequestBody String data) {
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
        visitCount += 1;
        hash.put("visit_count", visitCount);
        return hash;
    }

    EmailAddress emailAddress = new EmailAddress();

    @PostMapping(value = "/register_email", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EmailAddress registerEmail(@RequestBody String email) {
        String[] sepEmail = email.split(",");

        for(String sep : sepEmail) {
            String trimedEmail = sep.trim();
            if (!trimedEmail.equals("")) {
                emailAddress.setEmail_address(trimedEmail);
            }
        }

        return emailAddress;
    }

    @PostMapping(value = "/get_seven_json", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HashMap<String, Object> sevenJson(@RequestBody SevenJson sevenJson) {
        HashMap<String, Object> hash = new HashMap<String, Object>();
        hash.put("result", "success");
        hash.put("code", 1001);
        return hash;
    }

    @PostMapping(value = "/get_eight_json", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EightResult eightJson(@RequestBody HashMap<String, Object> eightJson) {
        EightResult er = new EightResult();
        er.setResult("created");
        er.setId("a1234");
        er.setSuccess(true);
        return er;
    }


    @PostMapping("/book_info")
    @ResponseBody
    public BookInfoResult bookInfo(@RequestBody BookInfo bookInfo){
        System.out.println(bookInfo);
        BookInfoResult res = new BookInfoResult();
        res.setId("a1234");
        res.setResult("created");
        res.setSuccess(true);

        return res;
    }

    @PostMapping(value = "/print_times", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody String printTimes(@RequestBody HashMap<String, Object> hash) {
        String str = (String)(hash.get("text"));
        int num = (Integer)(hash.get("count"));

        String res = "";

        for(int i = 0; i < num; i++) {
            res += str;
        }
        return res;
    }

    ArrayList<Vote> votes = new ArrayList<>();

    @PatchMapping(value = "/vote/register_option", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void registOption(@RequestBody VoteCreationDTO option) {
        Vote newOption = new Vote(option.getOption());
        votes.add(newOption);

        for(Vote v : votes) {
            System.out.println(v.option + "\t" + v.count);
        }
    }

    @PostMapping(value = "/vote/make_vote")
    @ResponseBody
    public void makeVote(@RequestParam("option") int idx) {
        Vote optionVote = votes.get(idx);
        optionVote.vote();
        System.out.println(optionVote.option + "\t" + optionVote.count);
    }

    @GetMapping(value = "/vote/show_options", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public OptionClass getOptions() {
        OptionClass optionClass = new OptionClass();
        for(Vote v: votes){
            optionClass.addVote(v);
        }
        System.out.println(optionClass);

        return optionClass;
    }

//    @GetMapping("/vote/show_options")
//    @ResponseBody
//    public String getOptions() {
//        return "";
//    }

    String wise_saying = "";

    @GetMapping(value = "/wise-string", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getWiseSaying() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create("http://api.kanye.rest"));

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        String responseBody = response.getBody();

        return responseBody;
    }

    @GetMapping(value = "/wise-class")
    @ResponseBody
    public WiseSaying getWiseSayingClass() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<Void> requestEntity = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create("http://api.kanye.rest"));

        ResponseEntity<WiseSaying> responseEntity = restTemplate.exchange(requestEntity, WiseSaying.class);
        WiseSaying wiseSaying = responseEntity.getBody();

        return wiseSaying;

    }

    @PostMapping(value="/files/upload", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> uploadFile(
            // 파일 외의 나머지 부분
            @RequestParam Map<String, Object> nonFileParts,
            // 파일 부분 (MultipartFile 클래스로 추상화)
            @RequestParam("file") MultipartFile file) throws IOException {
        // 파일 외의 나머지 부분 내용 출력
        for(String key : nonFileParts.keySet()) {
            System.out.println(key + " : " + nonFileParts.get(key).toString());
        }
        // 파일 이름 (+확장자)
        String originalFileName = file.getOriginalFilename();
        int dotIndex = originalFileName.lastIndexOf('.');
        // 파일 이름과 확장자 분리
        String filename = originalFileName.substring(0, dotIndex);
        String extension = originalFileName.substring(dotIndex + 1);
        System.out.println("filename : " + originalFileName + ", ext : " + extension);
        long unixTimeMillis = System.currentTimeMillis();
        // 새로운 파일 이름 생성
        String uploadedFileName = filename + "_" + unixTimeMillis + "." + extension;
        // uploaded라는 폴더가 루트 폴더에 존재한다고 가정하고 해당 위치에 파일 생성(=업로드)
        File uploadedFile = new File("uploaded/" + uploadedFileName);
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());
        fos.close();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("src") String src) throws IOException {
        System.out.println(src);
        // 업로드 폴더의 파일 찾기
        File downloadedFile = new File("uploaded/" + src);

        if (!downloadedFile.isFile() || !downloadedFile.exists()) {
            // 폴더이거나 파일이 없으면 404 에러
            return ResponseEntity.notFound().build();
        } else {
            // 파일이고, 파일이 존재하는 경우에만 FIS 이용하여 바이트를 바디로 전달
            FileInputStream fis = new FileInputStream(downloadedFile);
            // 직접 헤더 설정하고
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/octet-stream");
            headers.add("Content-Disposition", "attachment; filename=" + downloadedFile.getName());
            // new로 ResponseEntity 객체를 만들어서 주되, 각각 (바디, 헤더, 응답코드) 전달
            return new ResponseEntity<>(fis.readAllBytes(), headers, HttpStatus.OK);
        }

    }
}
