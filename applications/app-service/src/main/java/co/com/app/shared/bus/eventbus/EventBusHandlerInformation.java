package co.com.app.shared.bus.eventbus;

import co.com.app.model.shared.bus.eventbus.SyncEventDataBus;
import co.com.app.model.shared.bus.eventbus.SyncEventHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class EventBusHandlerInformation {

    private final ApplicationContext applicationContext;

    private final Map<Class<?>, List<Class<? extends SyncEventHandler<?>>>> indexedHandlers = new ConcurrentHashMap<>();

    @PostConstruct
    @SuppressWarnings("unchecked")
    void index() {
        Map<String, SyncEventHandler> handlers = applicationContext.getBeansOfType(SyncEventHandler.class);

        Map<Class<?>, List<Class<? extends SyncEventHandler<?>>>> index = new HashMap<>();

        for (SyncEventHandler<?> handler : handlers.values()) {
            Class<?> handlerClass = handler.getClass();

            ResolvableType type = ResolvableType.forClass(handlerClass).as(SyncEventHandler.class);
            Class<?> eventClass = type.getGeneric(0).resolve();

            if (eventClass == null || !SyncEventDataBus.class.isAssignableFrom(eventClass)) {
                continue;
            }

            index.computeIfAbsent(eventClass, k -> new ArrayList<>())
                    .add((Class<? extends SyncEventHandler<?>>) handlerClass);
        }

        indexedHandlers.clear();
        indexedHandlers.putAll(index.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> List.copyOf(e.getValue()))));
    }

    public List<Class<? extends SyncEventHandler<?>>> findAll(Class<? extends SyncEventDataBus> eventClass) {
        return indexedHandlers.getOrDefault(eventClass, List.of());
    }
}
