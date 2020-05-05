package ictgradschool.web.lab18;

import java.util.Arrays;

public class NoSaltBruteForce {
    public static void main(String[] args) {
        // 999 (worst case scenario for 3 digits) - T8/ZAQYZ4gw/lKANkFA+qAe8DLlcWQ/fxjhq9gUx0W9850c6XQav9ewJmIlcZtU84tVwR5+UKGyQ8V0xi5zZkg==
        // 9999 (worst case scenario for 4 digits) - thJlxqVh6QR273i+PSzqdoMGr6VaLlEMWRAI+Nw+b4qVIGh+X9OAXi2mfPXFiqFgP5YOAnsNUwf9f5i2c90XLw==

        final byte[] hashToMatch = Passwords.base64Decode("D9YGttrQaPDx4GWd5L6M1Gv9Q0Oyrvbpausn5PVUAEmANTMNMbIj94xfFmT9hWx53L89o16MZD7bPEEcCT0AGQ==");

        final String[] characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwzyz0123456789".split("");

        final long start_time = System.nanoTime();

        final int MAX_PASS_LENGTH = 5;

        int[] charIndex = new int[MAX_PASS_LENGTH];
        Arrays.fill(charIndex, -1);

        while (true) {
            String passwordUndertest = Util.getNextPassword(characterSet, charIndex);
            //System.out.println("Trying: " + passwordUndertest);

            if (passwordUndertest == null) {
                Util.printTimeElapsedWithMessageAndExit("No password was found that matched the provided hash", start_time, System.nanoTime(), 1);
            }

            if (Passwords.isInsecureHashMatch(passwordUndertest, hashToMatch)) {
                Util.printTimeElapsedWithMessageAndExit("Password is: " + passwordUndertest, start_time, System.nanoTime(), 0);
            }
        }
    }
}
