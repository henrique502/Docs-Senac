package br.com.hrdev.docsclient.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author henriqueschmidt
 */
public class ValidationHelper {
    
    private static final String PATTERN_IP = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean validateIPV4(final String ip){          

          Pattern pattern = Pattern.compile(PATTERN_IP);
          Matcher matcher = pattern.matcher(ip);
          return matcher.matches();             
    }
}
