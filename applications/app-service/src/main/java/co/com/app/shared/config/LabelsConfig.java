package co.com.app.shared.config;


import co.com.app.model.shared.bus.model.labels.EventHandlerService;
import co.com.app.model.shared.bus.model.labels.QueryService;
import co.com.app.model.shared.bus.model.labels.QueryServicePrimitive;
import co.com.app.model.shared.common.model.labels.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.app.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = QueryService.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = QueryServicePrimitive.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = EventHandlerService.class)
        },
        useDefaultFilters = false)
public class LabelsConfig {
}
