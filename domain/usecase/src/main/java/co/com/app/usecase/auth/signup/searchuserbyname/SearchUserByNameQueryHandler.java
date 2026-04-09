package co.com.app.usecase.auth.signup.searchuserbyname;




import co.com.app.model.shared.bus.model.labels.QueryServicePrimitive;
import co.com.app.model.shared.bus.query.QueryHandlerPrimitive;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@QueryServicePrimitive
@AllArgsConstructor
public class SearchUserByNameQueryHandler implements QueryHandlerPrimitive<SearchUserByNameQuery,String> {

    private final SearchUserByNameUseCase useCase;

    @Override
    public Mono<String> handlerPrimitive(SearchUserByNameQuery query) {

        return useCase.execute(query.getQuery())
                .flatMap(result ->
                  Mono.just(result.payload()));
    }

}
