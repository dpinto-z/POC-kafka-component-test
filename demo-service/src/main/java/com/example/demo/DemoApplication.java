package com.example.demo;

import com.example.demo.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@AllArgsConstructor
public class DemoApplication {

    private DemoService service;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> pong(@RequestParam(value = "ping", required = false,
            defaultValue = "ping") String ping) {
        service.publish(ping);
        return ResponseEntity.ok(String.format("reply %s", ping));
    }
}