package com.example.mvc;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

class CalcClass {
    private int num1;
    private int num2;
    private String op;
    private int result;

    public CalcClass(int num1, int num2, String op) {
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
        this.result = getResult(num1, num2, op);
    }

    public int getResult(int num1, int num2, String op) {
        if (op.equals("+")) return num1 + num2;
        else if(op.equals("-")) return num1 - num2;
        else if(op.equals("/")) return num1 / num2;
        else if(op.equals("*")) return num1 * num2;
        else return 0;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public String getOp() {
        return op;
    }

    public int getResult() {
        return result;
    }
}

class VisitCountClass {
    private int visit_count;

    public VisitCountClass(int visit_count) {
        this.visit_count = visit_count;
    }

    public int getVisit_count() {
        return visit_count;
    }

    public void setVisit_count(int visit_count) {
        this.visit_count = visit_count;
    }
}

class RegisterEmailClass {
    private ArrayList<String> email_addresses;

    public RegisterEmailClass() {
        this.email_addresses = new ArrayList<String>();
    }

    public void addEmailAddress(String emailAddress) {
        this.email_addresses.add(emailAddress);
    }

    public ArrayList<String> getEmail_addresses() {
        return email_addresses;
    }
}

class GetJsonClass {
    private String message;
    private String from;
    private String to;
    private int importance;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

class GetJsonResponse {
    private String result;
    private int code;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

class VoteClass {
    public String option;
    public int count;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class VoteResultClass {
    private ArrayList<VoteClass> result;

    public VoteResultClass() {
        this.result = new ArrayList<>();
    }

    public ArrayList<VoteClass> getResult() {
        return result;
    }

    public void setResult(ArrayList<VoteClass> result) {
        this.result = result;
    }

    public void addVote(VoteClass vote) {
        this.result.add(vote);
    }
}

@RestController
@RequestMapping("/practice")
public class Practice {
    // 1
    @GetMapping("/get_test")
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>("GET", null, HttpStatus.OK);
    }

    @PostMapping("/post_test")
    public ResponseEntity<String> postTest() {
        return new ResponseEntity<>("POST", null, HttpStatus.OK);
    }

    @PutMapping("/put_test")
    public ResponseEntity<String> putTest() {
        return new ResponseEntity<>("PUT", null, HttpStatus.OK);
    }

    @PatchMapping("/patch_test")
    public ResponseEntity<String> patchTest() {
        return new ResponseEntity<>("PATCH", null, HttpStatus.OK);
    }

    @DeleteMapping("/delete_test")
    public ResponseEntity<String> deleteTest() {
        return new ResponseEntity<>("DELETE", null, HttpStatus.OK);
    }

    // 2
    private String message;

    @PatchMapping("/update_message")
    public ResponseEntity<String> updateMessage(@RequestParam String message) {
        this.message = message;
        return new ResponseEntity<>(message, null, HttpStatus.OK);
    }

    @PatchMapping("/update_message/{message}")
    public ResponseEntity<String> updateMessagePathVariable(@PathVariable String message) {
        this.message = message;
        return new ResponseEntity<>(message, null, HttpStatus.OK);
    }

    @PatchMapping("/update_message/body")
    public ResponseEntity<String> updateMessageBody(@RequestBody String message) {
        this.message = message;
        return new ResponseEntity<>(message, null, HttpStatus.OK);
    }

    // 3
    @PostMapping("/calc")
    public ResponseEntity<CalcClass> calcResult(@RequestParam("num1") int num1, @RequestParam("num2") int num2, @RequestParam("op") String op) {
        CalcClass calcClass = new CalcClass(num1, num2, op);
        return new ResponseEntity<>(calcClass, null, HttpStatus.OK);
    }

    // 4
    private int visitCount = 0;
    @PostMapping("/update_visit_count")
    public ResponseEntity<Integer> countVisit() {
        this.visitCount += 1;
        return new ResponseEntity<>(visitCount, null, HttpStatus.OK);
    }

    // 5
    @PostMapping(value = "/update_visit_count_json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VisitCountClass> countVisitJSON() {
        this.visitCount += 1;
        VisitCountClass visitCountClass = new VisitCountClass(visitCount);
        return new ResponseEntity<>(visitCountClass, null, HttpStatus.OK);
    }

    // 6
    @PostMapping("/register_email")
    public ResponseEntity<RegisterEmailClass> registEmail(@RequestBody String emails) {
        RegisterEmailClass registerEmailClass = new RegisterEmailClass();
        String[] emailArray = emails.split(",");

        for(String emailAddress : emailArray) {
            registerEmailClass.addEmailAddress(emailAddress);
        }

        return new ResponseEntity<>(registerEmailClass, null, HttpStatus.OK);
    }

    // 7
    @PostMapping("/get_json")
    @ResponseBody
    public ResponseEntity<GetJsonResponse> getJsonResponse(@RequestBody GetJsonClass getJsonClass) {
        GetJsonResponse getJsonResponse = new GetJsonResponse();
        getJsonResponse.setResult("success");
        getJsonResponse.setCode(1001);

        return new ResponseEntity<>(getJsonResponse, null, HttpStatus.OK);
    }

    // 8
    @GetMapping(value = "/repeat_word", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> repeatWord(@RequestBody  HashMap<String, Object> requestData) {
        String text = (String)requestData.get("text");
        int count = (Integer)requestData.get("count");

        String result = text.repeat(count);

        return new ResponseEntity<>(result, null, HttpStatus.OK);
    }
    // 11
    ArrayList<VoteClass> votes = new ArrayList<>();

    @PatchMapping("/vote/regisiter_option")
    public ResponseEntity<VoteClass> addVote(@RequestBody String vote) {
        VoteClass voteClass = new VoteClass();
        voteClass.setOption(vote);
        voteClass.setCount(0);

        votes.add(voteClass);

        return new ResponseEntity<>(voteClass, null, HttpStatus.OK);
    }

    @GetMapping("/vote/show_options")
    public ResponseEntity<VoteResultClass> getVotes() {
        VoteResultClass voteResultClass = new VoteResultClass();
        for(VoteClass vote : votes) {
            voteResultClass.addVote(vote);
        }

        return new ResponseEntity<>(voteResultClass, null, HttpStatus.OK);
    }

    @PostMapping("/vote/make_vote")
    @ResponseBody
    public ResponseEntity<VoteClass> makeVote(@RequestParam("idx") int idx) {
        VoteClass vote = votes.get(idx);
        vote.setCount(vote.getCount() + 1);

        return new ResponseEntity<>(vote, null, HttpStatus.OK);
    }
}
