package com.zlg.zlgpm.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @GetMapping("/getMsg")
    public ResponseEntity<String> getMsg() {
        return ResponseEntity.ok("I`m back");
    }

}
