package ictgradschool.web.lab18;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println("Please input a username: ");
        String userName = Keyboard.readInput();

        System.out.println("Please input a password: ");
        String password = Keyboard.readInput();


        System.out.println("Creating hashes for: " + password);
        //char[] password = "asdf".toCharArray();
        byte[] salt = Passwords.getNextSalt();
        System.out.println("The salt is: " + Passwords.base64Encode(salt));

        byte[] insecureHash = Passwords.insecureHash(password);
        byte[] saltedHash = Passwords.hash(password.toCharArray(), salt);
        System.out.println("The salted hash is: " + Passwords.base64Encode(saltedHash));
        System.out.println("The insecure hash is: " + Passwords.base64Encode(insecureHash));

        //String s = Passwords.base64Encode(p);
        //System.out.println("Hashed password: " + s);
        //System.out.println(Passwords.isExpectedPassword(password, salt, p));

        System.out.println("Testing stored salt match: " + Passwords.isExpectedPassword(password.toCharArray(), salt, saltedHash));
        System.out.println("Testing random salt match: " + Passwords.isExpectedPassword(password.toCharArray(), Passwords.getNextSalt(), saltedHash));

        SecureUser testUser = new SecureUser(saltedHash, salt, userName);

        System.out.println("Please input password to test new user '" + testUser.getUserName() + "': ");
        String testPassword = Keyboard.readInput();

        System.out.println(testUser.isPasswordMatch(testPassword));

    }
}
