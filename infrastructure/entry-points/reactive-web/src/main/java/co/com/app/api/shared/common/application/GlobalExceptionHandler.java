package co.com.app.api.shared.common.application;


import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Order(-2)
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalExceptionHandler(ErrorAttributes errorAttributes,
                                  ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer) {

        super(errorAttributes, new WebProperties.Resources(), applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return route(all(), request -> {

            Throwable error = getError(request);

            Map<String, Object> errorPropertiesMap = getErrorAttributes(
                    request,
                    org.springframework.boot.web.error.ErrorAttributeOptions.defaults()
            );

            String message = error != null && error.getMessage() != null
                    ? error.getMessage()
                    : "Unexpected error";

            errorPropertiesMap.put("message", message);

            int status = resolveStatus(error, errorPropertiesMap);

            errorPropertiesMap.put("status", status);

            return ServerResponse
                    .status(HttpStatus.valueOf(status))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(errorPropertiesMap);
        });
    }

    private int resolveStatus(Throwable error,
                              Map<String, Object> errorPropertiesMap) {

        if (error instanceof IllegalArgumentException ||
                error instanceof RuntimeException) {

              return HttpStatus.BAD_REQUEST.value();
        }
        return (int) errorPropertiesMap.getOrDefault("status", 500);
    }
}
