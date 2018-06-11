package ru.rsreu.ars.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
    public static byte[] getChecksum(String filePathZip, String filePathConfig) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        try (InputStream is = Files.newInputStream(Paths.get(filePathZip)); InputStream iss = Files.newInputStream(Paths.get(filePathZip))) {
            DigestInputStream dis = new DigestInputStream(is, md) {
  /* Read decorated stream (dis) to EOF as normal... */
            };
            DigestInputStream dise = new DigestInputStream(iss, md) {
  /* Read decorated stream (dis) to EOF as normal... */
            };
            return md.digest();
        }
    }


    private static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;

        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
        return complete.digest();
    }

    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(String fileNameZip, String fileNameConfiguration, String template, String report) throws Exception {
        byte[] b = createChecksum(fileNameZip);
        byte[] c = createChecksum(fileNameConfiguration);
        byte[] t = createChecksum(template);
        byte[] r = createChecksum(report);
        StringBuilder result = new StringBuilder();

        for (byte aB : b) {
            result.append(Integer.toString((aB & 0xff) + 0x100, 16).substring(1));
        }
        result.append(":");
        for (byte aC : c) {
            result.append(Integer.toString((aC & 0xff) + 0x100, 16).substring(1));
        }
        result.append(":");
        for (byte aT : t) {
            result.append(Integer.toString((aT & 0xff) + 0x100, 16).substring(1));
        }
        result.append(":");
        for (byte aR : r) {
            result.append(Integer.toString((aR & 0xff) + 0x100, 16).substring(1));
        }

        return result.toString();
    }
}

