package Util;

public enum EmailConfig {
    EMAIL("email", "email to send emails", "alexalematt@gmail.com"),

    PASSWORD("password", "The password to send emails", "alealexmatt3!");

    public String propertyName;
    public String description;
    public String value;

    EmailConfig(String property, String description, String defaultValue){
        this.propertyName = property;
        this.description = description;
        this.value = defaultValue;
    }
}
