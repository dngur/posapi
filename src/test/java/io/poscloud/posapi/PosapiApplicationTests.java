package io.poscloud.posapi;

import io.poscloud.posapi.config.JasyptConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PosapiApplicationTests {
    @Test
    void contextLoads() {
    }

    @Test
    void testDecryption() {
        String buf = "";
        JasyptConfig jasyptConfig = new JasyptConfig();
        String encBuf = jasyptConfig.stringEncryptor().encrypt(buf);
        //log.debug(">>> TEST : Encrypt [{}]", encBuf);
        String decBuf = jasyptConfig.stringEncryptor().decrypt(encBuf);
        //log.debug(">>> TEST : Decrypt [{}]", decBuf);
        assert(buf.equals(decBuf));
    }
}
