package co.com.app.model.shared.cqrs;

public record Command<P, C>(P payload, C context) {
}