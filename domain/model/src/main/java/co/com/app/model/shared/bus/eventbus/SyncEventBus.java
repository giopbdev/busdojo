package co.com.app.model.shared.bus.eventbus;

import reactor.core.publisher.Mono;

/**
 * Contrato del bus de eventos síncronos.
 *
 * <p>Permite publicar un evento de dominio sin conocer quién lo va a procesar.
 * El caso de uso solo llama {@code notify} y el bus se encarga de encontrar
 * y ejecutar todos los {@link SyncEventHandler} registrados para ese tipo de evento.
 *
 * <p>Ejemplo de uso desde un caso de uso:
 * <pre>{@code
 * eventBus.notify(new UserSignedUpEvent(name, email));
 * }</pre>
 *
 * @see SyncEventDataBus
 * @see SyncEventHandler
 */
public interface SyncEventBus {

    /**
     * Publica un evento y lo despacha a todos los handlers suscritos.
     *
     * @param event el evento a publicar, debe implementar {@link SyncEventDataBus}
     * @return {@code Mono<Void>} que completa cuando todos los handlers terminaron
     */
    Mono<Void> notify(SyncEventDataBus event);
}
