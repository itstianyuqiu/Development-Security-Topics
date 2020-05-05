package ictgradschool.web.lab18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SlowHashGuesser {
    public static void main(String[] args) {
        final long start_time = System.nanoTime();

        try (BufferedReader fileReader = new BufferedReader(new FileReader("hash_input"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                final byte[] hashToMatch = Passwords.base64Decode(line);
                final String[] characterSet = "abcdefghijklmnopqrstu".split("");

                final int MAX_PASS_LENGTH = 5;

                int[] charIndex = new int[MAX_PASS_LENGTH];
                Arrays.fill(charIndex, -1);

                while (true) {
                    String passwordUndertest = Util.getNextPassword(characterSet, charIndex);

                    if (Passwords.isInsecureHashMatch(passwordUndertest, hashToMatch)) {
                        System.out.println(line + " => " + passwordUndertest);
                        break;

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Util.printTimeElapsedWithMessageAndExit("Completed", start_time, System.nanoTime(), 0);
    }
}
