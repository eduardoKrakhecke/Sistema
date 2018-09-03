package sis.security;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Crypto {

    public String getSHA256Hash(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes("UTF-8"));
        return bytesToHex(hash); // make it printable
    }

    private String bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
    }

    public static String decode(String value) throws Exception {
        byte[] decodedValue = Base64.getDecoder().decode(value);  // Basic Base64 decoding
        return new String(decodedValue, StandardCharsets.UTF_8);
    }
}
