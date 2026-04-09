package co.com.app.api.auth.signup.application;


import co.com.app.api.auth.signup.domain.UserSignUpRequest;
import co.com.app.model.auth.signup.model.UserSignUp;
import co.com.app.model.shared.cqrs.Command;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.usecase.auth.signup.SignUpTradicional;
import co.com.app.usecase.auth.signup.SignUpBus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class SignUpHandler {

    private final SignUpBus useCase;



    public Mono<ServerResponse> execute(ServerRequest serverRequest){

         var messageId  = serverRequest.headers().firstHeader("message-id");

         var response = serverRequest.bodyToMono(UserSignUpRequest.class)
                 .map(request -> getCommand(messageId, request))
                 .flatMap(useCase::execute)
                 .then(ServerResponse.status(HttpStatus.CREATED)
                         .build());

         return  response;
    }

    private static Command<UserSignUp, ContextData> getCommand(String messageId,
                                                               UserSignUpRequest userRequest) {

            var context = new ContextData(messageId);
            return  new Command<UserSignUp, ContextData>(UserSignUp.create(userRequest.getUsername(),
                    userRequest.getEmail(),
                    userRequest.getPassword()), context);

    };

}

