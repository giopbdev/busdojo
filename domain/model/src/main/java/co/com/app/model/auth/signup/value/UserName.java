package co.com.app.model.auth.signup.value;

public record UserName(String value) {

    public UserName {
        if (value == null) {
            throw new IllegalArgumentException("UserName no puede ser null");
        }
    }
}