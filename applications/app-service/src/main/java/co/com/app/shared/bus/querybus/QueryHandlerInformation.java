package co.com.app.shared.bus.querybus;


import co.com.app.model.shared.bus.model.labels.QueryService;
import co.com.app.model.shared.bus.query.QueryDataBus;
import co.com.app.model.shared.bus.query.QueryHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueryHandlerInformation {
    private final Map<Class<? extends QueryDataBus>, Class<? extends QueryHandler>> indexedHandlers;
    private final ApplicationContext applicationContext;

    public QueryHandlerInformation(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.indexedHandlers = new HashMap<>();
        registerQueryHandlers();
    }

    private void registerQueryHandlers() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(applicationContext::getType)
                .filter(beanType -> beanType != null && beanType.isAnnotationPresent(QueryService.class))
                .forEach(beanType -> {
                    var genericType = (ParameterizedType) beanType.getGenericInterfaces()[0];
                    var queryType = (Class<? extends QueryDataBus>) genericType.getActualTypeArguments()[0];
                    indexedHandlers.put(queryType, (Class<? extends QueryHandler>) beanType);
                });
    }

    public Class<? extends QueryHandler> search(Class<? extends QueryDataBus> queryClass) {
        var queryHandler = indexedHandlers.get(queryClass);
        if (queryHandler == null) {
            return null;
//            throw new RuntimeException("The command handler does not exist for the command: " + queryClass.getName());
        }
        return queryHandler;
    }





}
