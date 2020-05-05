package ictgradschool.web.lab18;

public class SecureUser {

    private byte[] hash;
    private byte[] salt;
    private String userName;

    public SecureUser(byte[] hash, byte[] salt, String userName) {
        this.hash = hash;
        this.salt = salt;
        this.userName = userName;
        System.out.println("Secure user created with userName: " + userName);
        System.out.println(this.getUserDetails());
    }

    public byte[] getHash() {

        return hash;
    }

    public byte[] getSalt() {

        return salt;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserDetails(){
        return (
                "\n\nUser details are: " + "\n" +
                "User name: " + this.getUserName() + "\n" +
                "User hash: " + Passwords.base64Encode(this.getHash()) + "\n" +
                "User salt: " + Passwords.base64Encode(this.getSalt()) + "\n" +
                "You can never see the password... you can only test if it matches..."
                );
    }

    public String isPasswordMatch (String password){
        char[] passwordCharArr = password.toCharArray();
//        return "METHOD NOT YET IMPLEMENTED, CANNOT CHECK: " + password;

//add a call to Passwords.isExpectedPassword to check if the entered password matches
        if (Passwords.isExpectedPassword(passwordCharArr,this.salt,getHash())){
           return "Entered password '" + password + "' matches the securely hashed and salted password.";
       } else {
           return "Entered password '" + password + "' does not match the securely hashed and salted password.";
       }

    }

}
