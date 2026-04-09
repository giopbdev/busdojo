package co.com.app.model.auth.signup.value;

public record UserEmail(String value) {

    public UserEmail {
        if (value == null) {
            throw new IllegalArgumentException("UserEmail no puede ser null");
        }
    }
}
