package com.example.fgs.otgdemo;

import java.io.UnsupportedEncodingException;

/**
 * Created by xiangstudio on 16-6-12.
 */
public class HexTools {

    public static String bytesToHexString(byte[] src, int len){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }

        int length = (len > src.length? src.length: len);
        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String DecodeUCS2(String src) {
        byte[] bytes = new byte[src.length() / 2];
        String reValue = "";
        int len = src.length();

        try {
            for (int i = 0; i < len; i += 2) {
                String val = src.substring(i, i + 2);
                bytes[i / 2] = (byte) (Integer.parseInt(val, 16));
            }
            reValue = new String(bytes, "UTF-16BE");
        } catch (UnsupportedEncodingException e) {
            e.toString();
        }
        return reValue;
    }
}
