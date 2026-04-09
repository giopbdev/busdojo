package co.com.app.model.shared.bus.command;

import reactor.core.publisher.Mono;

public interface CommandBus {

    Mono<Void> dispatch(CommandDataBus commandDataBus);
}
