package co.com.app.usecase.auth.signup.searchuserbyname;



import co.com.app.model.auth.signup.gateway.UserAuthSearchGateway;
import co.com.app.model.auth.signup.value.UserName;
import co.com.app.model.shared.common.domain.labels.UseCase;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@AllArgsConstructor
public class SearchUserByNameUseCase {

       private final UserAuthSearchGateway repository;

       public Mono<Query<String, ContextData>> execute(Query<UserName, ContextData> query){
           String name = query.payload().value();
           var response = repository.findByName(name)
                   .map(userSignUp -> new Query<String, ContextData>(userSignUp.name().value(), query.context()));
           return response;
       }
}
