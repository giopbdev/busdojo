package co.com.app.usecase.auth.signup.notification;

import co.com.app.model.shared.bus.eventbus.SyncEventHandler;
import co.com.app.model.shared.bus.model.labels.EventHandlerService;
import co.com.app.usecase.auth.signup.event.UserSignedUpEvent;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@EventHandlerService
@AllArgsConstructor
public class SendWelcomeNotificationHandler implements SyncEventHandler<UserSignedUpEvent> {

    private final SendWelcomeNotificationUseCase useCase;

    @Override
    public Mono<Void> handler(UserSignedUpEvent event) {
        return useCase.execute(event.name(), event.email());
    }
}
