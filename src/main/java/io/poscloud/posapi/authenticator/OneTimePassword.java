package io.poscloud.posapi.authenticator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static io.poscloud.posapi.authenticator.Hmac.getHmacSHA256;

@Slf4j
@Component
public class OneTimePassword {

    public boolean isOtpValid(String otp, String taxId) {

        String t1 = new SimpleDateFormat("HHmmss", Locale.KOREA).format(new Date());
        int sec = Integer.parseInt(t1.substring(4, 6));

        if (sec < 30) {
            t1 = t1.substring(0, 4) + "00";
        } else {
            t1 = t1.substring(0, 4) + "30";
        }

        // 1st round
        if (otp.equals(getOtp(t1, taxId))) return true;

        SimpleDateFormat f = new SimpleDateFormat("HHmmss", Locale.KOREA);
        Date d1 = f.parse(t1, new java.text.ParsePosition(0));

        // 2nd round
        String t0 = f.format(new Date(d1.getTime() - 30 * 1000));
        if (otp.equals(getOtp(t0, taxId))) return true;

        // 3rd round
        String t2 = f.format(new Date(d1.getTime() + 30 * 1000));
        return otp.equals(getOtp(t2, taxId));
    }

    private String getOtp(String timeStamp, String taxId) {

        String hmac = getHmacSHA256(taxId, timeStamp);
        long dec = Long.parseLong(hmac, 16);
        return String.format("%06d", dec % 1000000);
    }
}
