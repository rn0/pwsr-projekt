package chatServer;

import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * User: Piotr Kapera
 * Date: 2010-05-10
 * Time: 22:54:27
 */
public class Utils {
    public static long crc32(String content) {
        Checksum checksum = new CRC32();
        checksum.update(content.getBytes(),0, content.length());
        return checksum.getValue();
    }

    public static void log(String message) {
        System.out.println(message);
    }

    public static void log(Exception e) {
        System.out.println(e.toString());
    }

    public static String capitalize(String str) {
        String firstLetter = str.substring(0,1);  // Get first letter
        String remainder   = str.substring(1);    // Get remainder of word.
        return firstLetter.toUpperCase() + remainder.toLowerCase();
    }
}
