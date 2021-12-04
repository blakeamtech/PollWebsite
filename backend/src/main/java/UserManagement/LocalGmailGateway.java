package UserManagement;

import Interfaces.EmailGateway;

public class LocalGmailGateway implements EmailGateway {

    // sends email and returns token generated
    public String sendVerificationEmail(String email) {
        LocalGmail temp = new LocalGmail(email, "Verify Your Email", "Verification");
        temp.send();
        return temp.getToken();
    }

    public String sendForgotPasswordEmail(String email) {
        LocalGmail temp = new LocalGmail(email, "Change your Password", "Forgot");
        temp.send();
        return temp.getToken();
    }
}
