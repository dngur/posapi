package io.poscloud.posapi.authenticator;

import lombok.extern.slf4j.Slf4j;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class Hmac {

    public static String getHmacSHA256(String data, String key) {

        String resultBuf = "";
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            StringBuilder sb = new StringBuilder();
            for (byte b : mac.doFinal(data.getBytes())) {
                sb.append(String.format("%02x", b));
            }
            resultBuf = sb.toString();

        } catch (Exception e) {
            log.error(e.toString());
        }
        return resultBuf;
    }

    private String bytesToHex(byte[] bytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
