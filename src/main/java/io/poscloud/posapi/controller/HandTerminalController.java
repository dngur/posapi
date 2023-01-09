package io.poscloud.posapi.controller;

import com.google.gson.Gson;
import io.poscloud.posapi.schema.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/handterminal")
public class HandTerminalController {

    @PostMapping("/data")
    public String saveHandTerminalData(@RequestHeader Map<String, String> headers, @RequestBody String fileContents) throws Exception {

        CommonResponse response = new CommonResponse();

        String fileName = headers.get("file-name");
        String filePath = "C:/HTDATA/" + fileName.substring(0, 10) + "/";

        try {
            FileUtils.writeStringToFile(new File(filePath + fileName), fileContents, StandardCharsets.UTF_8);

            response.setCode("200");
            response.setMessage("ok");
        } catch (Exception e) {
            log.error("saveHandTerminalData() : {}", e.getLocalizedMessage());
            response.setCode("500");
            response.setMessage("error");
        }

        return new Gson().toJson(response, CommonResponse.class);
    }
}
