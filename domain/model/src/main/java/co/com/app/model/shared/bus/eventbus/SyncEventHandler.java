package co.com.app.model.shared.bus.eventbus;

import reactor.core.publisher.Mono;

/**
 * Contrato para los suscriptores del bus de eventos síncronos.
 *
 * <p>Cada handler se suscribe a un tipo específico de evento mediante el
 * genérico {@code <T>}. El bus usa ese genérico en tiempo de arranque para
 * construir el índice {@code evento → [handlers]}, sin necesidad de
 * registro manual.
 *
 * <p>Los handlers deben anotarse con {@code @EventHandlerService} para que
 * Spring los detecte y el bus los indexe al iniciar.
 *
 * <p>Ejemplo:
 * <pre>{@code
 * @EventHandlerService
 * @AllArgsConstructor
 * public class SendWelcomeNotificationHandler implements SyncEventHandler<UserSignedUpEvent> {
 *
 *     private final SendWelcomeNotificationUseCase useCase;
 *
 *     @Override
 *     public Mono<Void> handler(UserSignedUpEvent event) {
 *         return useCase.execute(new WelcomeNotification(event.name(), event.email()));
 *     }
 * }
 * }</pre>
 *
 * @param <T> tipo de evento que este handler procesa, debe implementar {@link SyncEventDataBus}
 * @see SyncEventBus
 * @see SyncEventDataBus
 */
public interface SyncEventHandler<T extends SyncEventDataBus> {

    /**
     * Procesa el evento recibido del bus.
     *
     * @param event el evento publicado
     * @return {@code Mono<Void>} que completa cuando el procesamiento termina
     */
    Mono<Void> handler(T event);
}

