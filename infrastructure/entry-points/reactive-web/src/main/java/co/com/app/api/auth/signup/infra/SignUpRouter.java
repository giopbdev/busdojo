package co.com.app.api.auth.signup.infra;


import co.com.app.api.auth.signup.application.SignUpHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SignUpRouter {

      @Bean
      public RouterFunction<ServerResponse> routes(SignUpHandler handler){

            return route()
                    .POST("api/v1/sign-up", handler::execute)
                    .build();
      }
}
