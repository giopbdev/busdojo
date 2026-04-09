package co.com.app.shared.bus.querybus;

import co.com.app.model.shared.bus.model.labels.QueryServicePrimitive;
import co.com.app.model.shared.bus.query.QueryDataBus;
import co.com.app.model.shared.bus.query.QueryHandlerPrimitive;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueryHandlerPrimitivesInformation {

    private final Map<Class<? extends QueryDataBus>, Class<? extends QueryHandlerPrimitive>> indexedHandlersPrimitives;
    private final ApplicationContext applicationContext;

    public QueryHandlerPrimitivesInformation(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.indexedHandlersPrimitives = new HashMap<>();

        registerQueryHandlersPrimitive();
    }


    private void registerQueryHandlersPrimitive() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(applicationContext::getType)
                .filter(beanType -> beanType != null && beanType.isAnnotationPresent(QueryServicePrimitive.class))
                .forEach(beanType -> {
                    var genericType = (ParameterizedType) beanType.getGenericInterfaces()[0];
                    var queryType = (Class<? extends QueryDataBus>) genericType.getActualTypeArguments()[0];
                    indexedHandlersPrimitives.put(queryType, (Class<? extends QueryHandlerPrimitive>) beanType);
                });
    }

    public Class<? extends QueryHandlerPrimitive> searchPrimitive(Class<? extends QueryDataBus> queryClass) {
        var queryHandler = indexedHandlersPrimitives.get(queryClass);
        if (queryHandler == null) {
//            throw new RuntimeException("The command handler does not exist for the command: " + queryClass.getName());
              return null;
        }
        return queryHandler;
    }


}
