package co.com.app.model.auth.signup.model;


import co.com.app.model.auth.signup.value.UserEmail;
import co.com.app.model.auth.signup.value.UserName;
import co.com.app.model.auth.signup.value.UserPassword;

public record UserSignUp(UserName name, UserEmail email, UserPassword password) {

       public static UserSignUp create(String name, String email, String password){

           return new UserSignUp(new UserName(name),
                                 new UserEmail(email),
                                 new UserPassword(password));
       }

}
