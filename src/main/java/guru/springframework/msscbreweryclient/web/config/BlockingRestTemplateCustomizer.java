package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {
    @Autowired
    RestTemplateProperties properties;
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        System.out.println("---> Getting connection max total...");
        System.out.println("---> Connection max total: " + properties.getConnectionMaxTotal());
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(properties.getConnectionMaxTotal());
        connectionManager.setDefaultMaxPerRoute(properties.getConnectionMaxPerRoute());

        RequestConfig requestConfig = RequestConfig
                .custom()
                .setConnectionRequestTimeout(properties.getRequestTimeout())
                .setSocketTimeout(properties.getRequestSocketTimeout())
                .build();

        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionManager(connectionManager)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.setRequestFactory(this.clientHttpRequestFactory());
    }
}
