package co.com.app.model.shared.bus.eventbus;

/**
 * Interfaz marcadora para los eventos de dominio del bus síncrono.
 *
 * <p>Todo evento que vaya a publicarse a través del {@link SyncEventBus}
 * debe implementar esta interfaz. Actúa como contrato de tipo para que
 * el bus pueda indexar y despachar correctamente los handlers.
 *
 * <p>Ejemplo:
 * <pre>{@code
 * public record UserSignedUpEvent(String name, String email) implements SyncEventDataBus {}
 * }</pre>
 *
 * @see SyncEventBus
 * @see SyncEventHandler
 */
public interface SyncEventDataBus {}

