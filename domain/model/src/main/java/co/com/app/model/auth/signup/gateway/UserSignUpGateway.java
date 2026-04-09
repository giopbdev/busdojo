package co.com.app.model.auth.signup.gateway;


import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UserSignUpGateway {

    Mono<Void> save(Command<UserSignUp, ContextData> command);
}
