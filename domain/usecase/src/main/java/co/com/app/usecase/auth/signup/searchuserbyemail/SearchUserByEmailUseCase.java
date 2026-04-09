package co.com.app.usecase.auth.signup.searchuserbyemail;



import co.com.app.model.auth.signup.gateway.UserAuthSearchGateway;
import co.com.app.model.auth.signup.value.UserEmail;
import co.com.app.model.shared.common.domain.labels.UseCase;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@AllArgsConstructor
public class SearchUserByEmailUseCase {

       private final UserAuthSearchGateway repository;

       public Mono<Query<String, ContextData>> execute(Query<UserEmail, ContextData> query){
           String email = query.payload().value();
           var response = repository.findByEmail(email)
                   .map(userSignUp -> {
                       System.out.println(userSignUp);
                       return new Query<String, ContextData>(userSignUp.email().value(), query.context());
                   });
           return response;
       }

}
