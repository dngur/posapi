package io.poscloud.posapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/handterminal")
public class HandTerminalController {

    @PostMapping("/data")
    public String saveHandTerminalData() throws Exception {





        return "aaaa";
    }
}
