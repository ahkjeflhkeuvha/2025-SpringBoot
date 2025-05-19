package com.example.mvc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

class GithubUser {
    private String login;
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}

class timeInfo {
    private String updatedISO;

    public String getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    @Override
    public String toString() {
        return "timeInfo{" +
                "updatedISO='" + updatedISO + '\'' +
                '}';
    }
}

class rateInfo {
    @JsonProperty("rate_float")
    private float rate_float;

    public float getRate_float() {
        return rate_float;
    }

    public void setRate_float(float rate_float) {
        this.rate_float = rate_float;
    }

    @Override
    public String toString() {
        return "rateInfo{" +
                "rate_float='" + rate_float + '\'' +
                '}';
    }
}

class priceInfo {
    @JsonProperty("USD")
    private rateInfo USD;

    public rateInfo getUsd() {
        return USD;
    }

    public void setUsd(rateInfo USD) {
        this.USD = USD;
    }

    @Override
    public String toString() {
        return "priceInfo{" +
                "usd=" + USD +
                '}';
    }
}

class bitcoinInfo {
    private timeInfo time;
    private priceInfo bpi;

    public timeInfo getTime() {
        return time;
    }

    public void setTime(timeInfo time) {
        this.time = time;
    }

    public priceInfo getBpi() {
        return bpi;
    }

    public void setBpi(priceInfo bpi) {
        this.bpi = bpi;
    }

    @Override
    public String toString() {
        return "bitcoinInfo{" +
                "time=" + time +
                ", bpi=" + bpi +
                '}';
    }
}

@RestController
@RequestMapping("/response_entity_demo")
public class ResponseEntityDemo {
    @GetMapping("/demo1")
    // 제네릭 타입이 Void인 이유는 해당 응답메시지에 바디데이터가 없기 때문임
    public ResponseEntity<Void> demo1() {
        // 상태 코드와 관련된 메서드와 build 메서드 호출하여 바로 바디 데이터 없이 응답
        return ResponseEntity.ok().build();
        // 바디데이터 없이 404 주고 싶은 경우
        // return ResponseEntity.notFound().build();
    }

    @GetMapping("/demo2")
    // 제네릭 타입이 String이니 바디에 평문(text/plain)이 들어갈 것임을 유추 가능
    public ResponseEntity<String> demo2() {
        String responseBody = "Hello, world!";
        // 상태 코드와 관련된 메소드에 바디 데이터를 같이 전달 가능
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/demo3")
    public ResponseEntity<String> demo3() {
        // 좀 더 세분화해서 응답메시지를 커스터마이징하고 싶은 경우
        // 헤더 영역 정보 만들기

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "value");
        String responseBody = "Hello, world!";
        return ResponseEntity
                .status(HttpStatus.OK) // 상태 코드
                .headers(headers) // 헤더
                .body(responseBody); // 바디
    }

    @GetMapping("/demo4")
    public ResponseEntity<String> demo4() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "value");
        String responseBody = "Hello, world!";
        // 빌더 패턴 없이 직접 new 키워드로 객체 생성하여 응답
        return new ResponseEntity<>(
                responseBody, // 바디
                headers, // 헤더
                HttpStatus.OK); // 상태 코드
    }

    @GetMapping(value = "/github/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GithubUser githubUser(@PathVariable("user") String user) {
        RestTemplate restTemplate = new RestTemplate();

        // 요청 메시지 생성 및 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        // 요청 메시지에 바디 데이터가 없으므로 Void 타입으로 설정
        RequestEntity<Void> requestEntity = new RequestEntity<>(
                null, headers, HttpMethod.GET, URI.create("https://api.github.com/users/" + user));

        // 응답 메시지
        // ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        ResponseEntity<GithubUser> response = restTemplate.exchange(requestEntity, GithubUser.class);
        // 응답 메시지의 바디 데이터를 문자열로 해석
        GithubUser responseBody = response.getBody();

        return responseBody;
    }

    @GetMapping(value = "/bitcoin", produces = MediaType.APPLICATION_JSON_VALUE)
    public bitcoinInfo bitcoin() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        RequestEntity<Void> requestEntity = new RequestEntity<>(null, headers, HttpMethod.GET, URI.create("https://my-json-server.typicode.com/akaz00/fake-api/bitcoin"));
        ResponseEntity<bitcoinInfo> response = restTemplate.exchange(requestEntity, bitcoinInfo.class);
        bitcoinInfo responseBody = response.getBody();

        System.out.println(responseBody);

        return responseBody;
    }
}
