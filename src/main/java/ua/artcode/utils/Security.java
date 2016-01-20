package ua.artcode.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Maxim on 19.01.2016.
 */
public class Security {
    public static String toMd5(String password) {
        return DigestUtils.md5Hex(password);
    }
}
