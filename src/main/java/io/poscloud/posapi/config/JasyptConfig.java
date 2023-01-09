package io.poscloud.posapi.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("+82-1o-2814-ooo1");          // 암호화할 때 사용하는 키
        config.setAlgorithm("PBEWithMD5AndDES");         // 암호화 알고리즘
        config.setKeyObtentionIterations(100);           // 반복할 해싱 회수
        config.setPoolSize(1);                           // 암호화 인스턴스 pool
        config.setStringOutputType("base64");            // 인코딩 방식

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);

        return encryptor;
    }
}
