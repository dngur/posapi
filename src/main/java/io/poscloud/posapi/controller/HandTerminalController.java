package io.poscloud.posapi.controller;

import com.google.gson.Gson;
import io.poscloud.posapi.schema.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1")
public class HandTerminalController {

    @PostMapping("/handterminal")
    public String saveHandTerminalData(@RequestHeader Map<String, String> headers, @RequestBody String fileContents) throws Exception {

        CommonResponse response = new CommonResponse();

        String fileName = headers.get("file-name");
        String filePath = "C:/HTDATA/" + fileName.substring(0, 10) + "/";

        Path savePath = Paths.get(filePath);
        if (!Files.exists(savePath)) {
            Files.createDirectories(savePath);
        }

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

    @PostMapping("/image")
    public String saveImage(@RequestHeader Map<String, String> headers, @RequestParam("image") MultipartFile multipartFile) throws Exception {

        CommonResponse response = new CommonResponse();

        String fileName = headers.get("file-name");
        String filePath = "C:/IMAGE/"; // + fileName.substring(0, 10) + "/";

        Path savePath = Paths.get(filePath);
        if (!Files.exists(savePath)) {
            Files.createDirectories(savePath);
        }

        try {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path path = savePath.resolve(fileName);
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                response.setCode("200");
                response.setMessage("ok");
            } catch (IOException ee) {
                log.error("saveImage() : {}", ee.getLocalizedMessage());
                response.setCode("500");
                response.setMessage("error");
            }
        } catch (Exception e) {
            log.error("saveImage() : {}", e.getLocalizedMessage());
            response.setCode("500");
            response.setMessage("error");
        }

        return new Gson().toJson(response, CommonResponse.class);
    }
}
