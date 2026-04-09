package co.com.app.usecase.auth.signup.searchuserbyemail;



import co.com.app.model.shared.bus.model.labels.QueryService;
import co.com.app.model.shared.bus.query.QueryHandler;
import co.com.app.model.shared.cqrs.ContextData;
import co.com.app.model.shared.cqrs.Query;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@QueryService
@AllArgsConstructor
public class SearchUserInfoQueryHandler implements
        QueryHandler<SearchUserByEmailQuery, ContextData, String> {

    private final SearchUserByEmailUseCase useCase;

    @Override
    public Mono<Query<String, ContextData>> handler(SearchUserByEmailQuery userInfoQuery) {

        return useCase.execute(userInfoQuery.getQuery());
    }




}
