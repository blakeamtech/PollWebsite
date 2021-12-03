package Interfaces;

public interface Email {

    // sends email
    public void send();

    // gets token of user for verification
    public String getToken();
}
