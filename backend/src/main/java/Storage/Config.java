package Storage;

public enum Config {
    DB_STRING("db_string", "The string to connect to the database", "\"mongodb://localhost:%s"),

    DB_PORT("db_port", "The port through which to connect to the database", "5432"),

    DB_PW("db_pw", "PW for local DB", System.getProperty("DB_PW")),

    DB_USERNAME("db_username", "User for local DB.", System.getProperty("DB_USER")),

    ID_ALLOWED_CHARACTERS("allowed_characters",
            "Allowed characters for random IDs.",
            "abcdefghjkmnpqrstvwxyz0123456789")
    ;

    public String propertyName;
    public String description;
    public Object value;

    Config(String property, String description, Object defaultValue){
        this.propertyName = property;
        this.description = description;
        this.value = defaultValue;
    }
}
