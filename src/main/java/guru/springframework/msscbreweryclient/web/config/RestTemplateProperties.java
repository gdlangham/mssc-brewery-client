package guru.springframework.msscbreweryclient.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties()
@Getter
@Setter
public class RestTemplateProperties {
    @Value("${rest-template.connection.max.total}")
    private int connectionMaxTotal;

    @Value("${rest-template.connection.max.per.route}")
    private int connectionMaxPerRoute;

    @Value("${rest-template.request.timeout}")
    private int requestTimeout;

    @Value("${rest-template.request.socket.timeout}")
    private int requestSocketTimeout;
}
