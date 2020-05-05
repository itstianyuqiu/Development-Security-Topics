package ictgradschool.web.lab18;

public class Util {
    public static String getNextPassword(String[] characterSet, int[] charIndex) {

        for (int i = 0; i < charIndex.length; i++) {
            charIndex[i] = charIndex[i] + 1;

            if (charIndex[i] >= characterSet.length) {
                if ((i + 1) == charIndex.length) {
                    return null;
                }

                charIndex[i] = 0;
            } else {
                break;
            }
        }

        String passwordUndertest = "";
        for (int aCharIndex : charIndex) {
            if (aCharIndex != -1) {
                passwordUndertest += characterSet[aCharIndex];
            }
        }
        return passwordUndertest;
    }

    public static void printTimeElapsedWithMessageAndExit(String message, long start, long end, int exit_code) {
        System.out.println(message);
        System.out.printf("Time elapsed: %.2f seconds\n", (end - start) / 1_000_000_000.0);
        System.exit(exit_code);
    }
}
