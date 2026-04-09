package co.com.app.shared.config;


import co.com.app.model.shared.bus.model.QueryService;
import co.com.app.model.shared.bus.model.QueryServicePrimitive;
import co.com.app.model.shared.common.domain.labels.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.app.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = UseCase.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = QueryService.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = QueryServicePrimitive.class)
        },
        useDefaultFilters = false)
public class LabelsConfig {
}
