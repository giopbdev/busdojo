package co.com.app.model.shared.bus.query;


import reactor.core.publisher.Mono;

public interface QueryHandlerPrimitive<REQ, RES> extends QueryDataBus {

    Mono<RES> handlerPrimitive(REQ query);
}
