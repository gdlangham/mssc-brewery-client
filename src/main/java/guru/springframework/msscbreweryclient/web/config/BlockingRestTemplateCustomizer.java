package guru.springframework.msscbreweryclient.web.config;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    @Override
    public void customize(final RestTemplate restTemplate) {

    }
}
