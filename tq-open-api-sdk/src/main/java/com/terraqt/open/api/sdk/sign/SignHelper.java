package com.terraqt.open.api.sdk.sign;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignHelper {
    private static final Logger logger = LogManager.getLogger(SignHelper.class);

    public static String calculateSignature(String key, String secret, String timestamp, String requestBody) {
        return calculateMd5(key + secret + timestamp + requestBody);
    }

    private static String calculateMd5(String content) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BigInteger bigInt = new BigInteger(1, md5.digest(content.getBytes()));
            return fillMd5(bigInt.toString(16).toUpperCase());
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Failed to calculate md5", ex);
        }
        return null;
    }

    private static String fillMd5(String md5){
        return md5.length() ==32 ? md5: fillMd5("0"+md5);
    }

}
