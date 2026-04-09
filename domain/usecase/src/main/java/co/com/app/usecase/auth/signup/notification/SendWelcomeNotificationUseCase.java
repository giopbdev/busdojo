package co.com.app.usecase.auth.signup.notification;

import co.com.app.model.shared.common.model.labels.UseCase;
import reactor.core.publisher.Mono;

@UseCase
public class SendWelcomeNotificationUseCase {

    public Mono<Void> execute(String name, String email) {
        return Mono.fromRunnable(() ->
                System.out.printf("[SendWelcomeNotification] Bienvenido %s, notificaci\u00f3n enviada a: %s%n", name, email)
        );
    }
}
