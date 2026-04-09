package co.com.app.inmemorydatabase.auth.signup.application;


import co.com.app.inmemorydatabase.auth.signup.domain.UserSignUpData;
import co.com.app.model.auth.signup.gateway.UserAuthSearchGateway;
import co.com.app.model.auth.signup.gateway.UserSignUpGateway;
import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignUpAdapter implements UserSignUpGateway, UserAuthSearchGateway {

    public List<UserSignUpData> users;

    public SignUpAdapter() {
        this.users = new ArrayList<>();
    }

    @Override
    public Mono<Void> save(Command<UserSignUp, ContextData> command) {

        return Mono.fromRunnable(() ->{
            String name = command.payload().name().value();
            String email = command.payload().email().value();
            String password = command.payload().password().value();
            var user = new UserSignUpData(name,email, password );
            users.add(user);
        });
    }

    @Override
    public Mono<UserSignUp> findByEmail(String email) {

        return Flux.fromIterable(users)
                .filter(userSignUpData -> userSignUpData.email().equalsIgnoreCase(email))
                .map(user ->  {
                    System.out.println(user);
                     return UserSignUp.create(user.name(), user.email(), user.password());
                })
                .next();
    }

    @Override
    public Mono<UserSignUp> findByName(String name) {
        return Flux.fromIterable(users)
                .filter(userSignUpData -> userSignUpData.name().equalsIgnoreCase(name))
                .map(user ->  {
                    System.out.println(user);
                    return UserSignUp.create(user.name(), user.email(), user.password());
                })
                .next();
    }
}
