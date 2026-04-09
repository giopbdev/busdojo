package co.com.app.usecase.auth.signup;

import co.com.app.model.auth.signup.gateway.UserSignUpGateway;
import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.auth.signup.value.UserEmail;
import co.com.app.model.auth.signup.value.UserName;
import co.com.app.model.shared.common.domain.labels.UseCase;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import co.com.app.usecase.auth.signup.searchuserbyemail.SearchUserByEmailUseCase;
import co.com.app.usecase.auth.signup.searchuserbyname.SearchUserByNameUseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@UseCase
@AllArgsConstructor
public class SignUpTradicional {


    private final SearchUserByEmailUseCase searchUserByEmail;
    private final SearchUserByNameUseCase searchUserByName;

    private final UserSignUpGateway repository;

    public Mono<Void> execute(Command<UserSignUp, ContextData> command) {
        return validateEmailNotExists(command)
                .then(validateNameNotExists(command))
                .then(Mono.defer(()-> repository.save(command)));
    }

    private Mono<Void> validateEmailNotExists(Command<UserSignUp, ContextData> command) {

        var query = new Query<UserEmail, ContextData>(
                command.payload().email(),
                command.context()
        );

        return searchUserByEmail.execute(query)
                .hasElement()
                .flatMap(exists -> exists
                        ? Mono.error(new RuntimeException("One user has the same email"))
                        : Mono.empty()
                );
    }

    private Mono<Void> validateNameNotExists(Command<UserSignUp, ContextData> command) {

        var query = new Query<UserName, ContextData>(
                command.payload().name(),
                command.context()
        );

        return searchUserByName.execute(query)
                .hasElement()
                .flatMap(exists -> exists
                        ? Mono.error(new RuntimeException("One user has the same name"))
                        : Mono.empty()
                );

    }

}
