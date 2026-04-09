package co.com.app.model.auth.signup.gateway;


import co.com.app.model.auth.signup.model.UserSignUp;
import reactor.core.publisher.Mono;

public interface UserAuthSearchGateway {

    Mono<UserSignUp> findByEmail(String email);
    Mono<UserSignUp> findByName(String name);

}
