package co.com.app.model.shared.cqrs;

public record Query<P, C>(P payload, C context) {
}