package utils;

import java.util.Random;

public class Utils {

    public static String genRandomString(){
        return genRandomStringWithLength(10);
    }



    private static String genRandomStringWithLength(int count) {
        String alphabet =
                new String("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        int n = alphabet.length();

        String result = new String();
        Random r = new Random();

        for (int i = 0; i < count; i++)
            result = result + alphabet.charAt(r.nextInt(n));

        return result;
    }

}
