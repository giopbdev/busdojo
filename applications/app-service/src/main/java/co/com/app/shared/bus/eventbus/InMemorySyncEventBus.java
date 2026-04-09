package co.com.app.shared.bus.eventbus;

import co.com.app.model.shared.bus.eventbus.SyncEventBus;
import co.com.app.model.shared.bus.eventbus.SyncEventDataBus;
import co.com.app.model.shared.bus.eventbus.SyncEventHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InMemorySyncEventBus implements SyncEventBus {

    private static final Logger log = LoggerFactory.getLogger(InMemorySyncEventBus.class);

    private final EventBusHandlerInformation handlerInformation;
    private final ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Void> notify(SyncEventDataBus event) {
        Class<? extends SyncEventDataBus> eventClass = (Class<? extends SyncEventDataBus>) event.getClass();
        List<Class<? extends SyncEventHandler<?>>> handlerClasses = handlerInformation.findAll(eventClass);

        if (handlerClasses.isEmpty()) {
            log.warn("No hay handlers registrados para el evento: {}", eventClass.getSimpleName());
            return Mono.empty();
        }

        log.info("[InMemorySyncEventBus] Evento recibido: {} -> {} handler(s) encontrado(s)",
                eventClass.getSimpleName(), handlerClasses.size());

        return Flux.fromIterable(handlerClasses)
                .flatMap(handlerClass -> {
                    log.info("[InMemorySyncEventBus] Despachando a handler: {}", handlerClass.getSimpleName());
                    return invokeHandler(handlerClass, event)
                            .doOnSuccess(v -> log.info("[InMemorySyncEventBus] Handler {} terminó exitosamente",
                                    handlerClass.getSimpleName()));
                }, handlerClasses.size())
                .onErrorResume(err -> {
                    log.error("[InMemorySyncEventBus] Fallo en el despacho del evento: {}", eventClass.getSimpleName(), err);
                    return Mono.empty();
                })
                .then();
    }

    @SuppressWarnings("unchecked")
    private Mono<Void> invokeHandler(Class<? extends SyncEventHandler<?>> handlerClass, SyncEventDataBus event) {
        SyncEventHandler<SyncEventDataBus> handler =
                (SyncEventHandler<SyncEventDataBus>) applicationContext.getBean(handlerClass);
        return handler.handler(event);
    }
}
