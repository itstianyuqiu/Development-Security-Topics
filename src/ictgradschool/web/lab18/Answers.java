package ictgradschool.web.lab18;

import java.io.*;

public class Answers {
    public static void main(String[] args) {
        System.out.println("Question 1");
        System.out.println("==========");
        System.out.println("Password: bad1  hash: " + Passwords.base64Encode(Passwords.insecureHash("bad1")));


        System.out.println("\nQuestion 2");
        System.out.println("==========");
        System.out.println("Password: foo  hash: " + Passwords.base64Encode(Passwords.hash("foo".toCharArray(), new byte[]{ 13 }, 5)));


        System.out.println("\nQuestion 3");
        System.out.println("==========");
        System.out.println("Password: no  hash: " + Passwords.base64Encode(Passwords.hash("no".toCharArray(), new byte[]{ 27 }, 500)));

        System.out.println("\nQuestion 4");
        System.out.println("==========");


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("passwords.txt"))) {
            try (PrintWriter b = new PrintWriter(new FileWriter("hash_input"))) {
                String pw;
                while ((pw = bufferedReader.readLine()) != null) {
                    b.println(Passwords.base64Encode(Passwords.insecureHash(pw)));
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
