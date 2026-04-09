package co.com.app.model.shared.bus.query;


import co.com.app.model.shared.cqrs.Query;
import reactor.core.publisher.Mono;

public interface QueryHandler<REQ, C, RES> extends QueryDataBus {

    Mono<Query<RES, C>> handler(REQ query);
}
