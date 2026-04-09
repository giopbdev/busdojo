package co.com.app.model.shared.bus.query;


import co.com.app.model.shared.cqrs.Query;
import reactor.core.publisher.Mono;

public interface QueryBus {

    Mono<Query> dispatch(QueryDataBus queryDataBus);
}
