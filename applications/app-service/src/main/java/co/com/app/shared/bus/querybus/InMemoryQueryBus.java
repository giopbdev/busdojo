package co.com.app.shared.bus.querybus;


import co.com.app.model.shared.bus.query.QueryBus;
import co.com.app.model.shared.bus.query.QueryDataBus;
import co.com.app.model.shared.cqrs.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;


@Configuration
@RequiredArgsConstructor
public class InMemoryQueryBus implements QueryBus {
    private final QueryHandlerInformation queryHandlerInformation;
    private final QueryHandlerPrimitivesInformation queryHandlerPrimitivesInformation;
    private final ApplicationContext applicationContext;


    @Override
    public Mono<Query> dispatch(QueryDataBus queryDataBus) {

        var handlerClass = queryHandlerInformation.search(queryDataBus.getClass());

        if(handlerClass == null){
           var handlerClassPrimitive =  queryHandlerPrimitivesInformation.searchPrimitive(queryDataBus.getClass());
           var handler = applicationContext.getBean(handlerClassPrimitive);
           return handler.handlerPrimitive(queryDataBus);
        }else{
            var handler = applicationContext.getBean(handlerClass);
            return handler.handler(queryDataBus)  ;
        }

    }


}
