package Interfaces;

public interface EmailGateway {

    // sends email
    public String sendVerificationEmail(String email);

    // gets token of user for verification
    public String sendForgotPasswordEmail(String email);
}
