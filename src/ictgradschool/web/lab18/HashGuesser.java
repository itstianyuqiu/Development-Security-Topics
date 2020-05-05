package ictgradschool.web.lab18;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class HashGuesser {
    public static void main(String[] args) {
        final String[] characterSet = "abcdefghijklmnopqrstu".split("");
        HashMap<String, String> passmap = new HashMap<>();

        long start_time = System.nanoTime();

        final int MAX_PASS_LENGTH = 5;

        int[] charIndex = new int[MAX_PASS_LENGTH];
        Arrays.fill(charIndex, -1);

        while (true) {
            String passwordUndertest = Util.getNextPassword(characterSet, charIndex);

            if (passwordUndertest == null) {
                break;
            }

            passmap.put(Passwords.base64Encode(Passwords.insecureHash(passwordUndertest)), passwordUndertest);
        }

        System.out.printf("Dictionary built in %.2f seconds\n", (System.nanoTime() - start_time) / 1_000_000_000.0);


        start_time = System.nanoTime();

        try (BufferedReader fileReader = new BufferedReader(new FileReader("hash_input"))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line + " => " + passmap.get(line));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Util.printTimeElapsedWithMessageAndExit("Completed", start_time, System.nanoTime(), 0);
    }
}
