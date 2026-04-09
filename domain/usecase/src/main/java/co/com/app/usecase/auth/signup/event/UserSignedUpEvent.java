package co.com.app.usecase.auth.signup.event;

import co.com.app.model.shared.bus.eventbus.SyncEventDataBus;

public record UserSignedUpEvent(String name, String email) implements SyncEventDataBus {
}
