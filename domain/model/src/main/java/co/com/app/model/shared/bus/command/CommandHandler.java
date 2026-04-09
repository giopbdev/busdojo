package co.com.app.model.shared.bus.command;

import reactor.core.publisher.Mono;

public interface CommandHandler<T> extends CommandDataBus {
    Mono<Void> handler(T command);
}
