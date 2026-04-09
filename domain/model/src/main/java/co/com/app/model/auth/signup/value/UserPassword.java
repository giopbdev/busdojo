package co.com.app.model.auth.signup.value;


public record UserPassword(String value) {

    public UserPassword {
        if (value == null) {
            throw new IllegalArgumentException("UserPassword no puede ser null");
        }
    }
}
