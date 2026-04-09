package co.com.app.usecase.auth.signup;

import co.com.app.model.auth.signup.gateway.UserSignUpGateway;
import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.auth.signup.value.UserName;
import co.com.app.model.shared.bus.query.QueryBus;
import co.com.app.model.shared.common.model.labels.UseCase;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import co.com.app.usecase.auth.signup.searchuserbyemail.SearchUserByEmailQuery;
import co.com.app.usecase.auth.signup.searchuserbyname.SearchUserByNameQuery;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@UseCase
@AllArgsConstructor
public class SignUpBus {


   // private final SearchUserByEmailUseCase searchUserByEmail;
   // private final SearchUserByNameUseCase searchUserByName;

    private QueryBus queryBus;

    private final UserSignUpGateway repository;

    public Mono<Void> execute(Command<UserSignUp, ContextData> command) {
        return validateEmailNotExists(command)
                .then(validateNameNotExists(command))
                .then(Mono.defer(()-> repository.save(command)));
    }

    private Mono<Void> validateEmailNotExists(Command<UserSignUp, ContextData> command) {


        return queryBus.dispatch(SearchUserByEmailQuery.fromCmd(command))
                .hasElement()
                .flatMap(exists -> exists
                        ? Mono.error(new RuntimeException("One user has the same email"))
                        : Mono.empty()
                );


    }

    private Mono<Void> validateNameNotExists(Command<UserSignUp, ContextData> command) {

//        var query = new Query<UserName, ContextData>(
//                command.payload().name(),
//                command.context()
//        );
        return queryBus.dispatch(SearchUserByNameQuery.fromCmd(command))
                .hasElement()
                .flatMap(exists -> exists
                        ? Mono.error(new RuntimeException("One user has the same name"))
                        : Mono.empty()
                );
//        return searchUserByName.execute(query)
//                .hasElement()
//                .flatMap(exists -> exists
//                        ? Mono.error(new RuntimeException("One user has the same name"))
//                        : Mono.empty()
//                );

    }

}
