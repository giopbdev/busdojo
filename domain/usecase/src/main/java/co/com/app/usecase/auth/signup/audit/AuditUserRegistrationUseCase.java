package co.com.app.usecase.auth.signup.audit;

import co.com.app.model.shared.common.model.labels.UseCase;
import reactor.core.publisher.Mono;

@UseCase
public class AuditUserRegistrationUseCase {

    public Mono<Void> execute(String name, String email) {
        return Mono.fromRunnable(() ->
                System.out.printf("[AuditUserRegistration] Registro auditado - nombre: %s, email: %s%n", name, email)
        );
    }
}
