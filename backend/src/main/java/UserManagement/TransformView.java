package UserManagement;

public class TransformView {
    // format email; Transform View pattern
    public static String transformEmail(String type, String token) {
        String action = type.equals("Verification") ? "verification" : "changepassword";

        return String.format("Hello, <br> Please click on the following link to verify your email! " +
                    "<br><br> http://localhost:3000/%s/" + token, action);
    }
}
