package ictgradschool.web.lab18;

import java.util.Arrays;

public class SmallSaltBruteForce {
    public static void main(String[] args) {
        final byte[] hashToMatch = Passwords.base64Decode("ocxgyfryiU2BTwWn50EO6GF5me7gDjvoKvTFiRExsEkxc7qjr8dQxT/nyMfKBv+DSvajSXPsfFCR9zVg4v9Ldg==");
        final String[] characterSet = "abcdefghijklmnopqrstuvwzyz0123456789".split("");

        final long start_time = System.nanoTime();

        final int MAX_PASS_LENGTH = 4;

        int[] charIndex = new int[MAX_PASS_LENGTH];
        Arrays.fill(charIndex, -1);

        while (true) {
            String passwordUndertest = Util.getNextPassword(characterSet, charIndex);

            if (passwordUndertest == null) {
                Util.printTimeElapsedWithMessageAndExit("No password was found that matched the provided hash", start_time, System.nanoTime(), 1);
            }

            byte[] salt = new byte[1];
            for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
                salt[0] = b;

                if (Passwords.isExpectedPassword(passwordUndertest.toCharArray(), salt, 5, hashToMatch)) {
                    Util.printTimeElapsedWithMessageAndExit("Password is: " + passwordUndertest, start_time, System.nanoTime(), 0);
                }
            }
        }
    }
}
