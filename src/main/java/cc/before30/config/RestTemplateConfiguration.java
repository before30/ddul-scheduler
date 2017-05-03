package cc.before30.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Created by before30 on 01/05/2017.
 */
@Configuration
@Slf4j
public class RestTemplateConfiguration {

    @Autowired
    private RestTemplateProperties properties;

    @Bean
    public RestTemplate restTemplate() {

        log.info("ct : {}, rt : {}", properties.getConnectionTimeout(), properties.getReadTimeout());
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder
                .setConnectTimeout(Optional.ofNullable(properties.getConnectionTimeout()).orElse(3000))
                .setReadTimeout(Optional.ofNullable(properties.getReadTimeout()).orElse(5000))
                .requestFactory(new OkHttp3ClientHttpRequestFactory())
                .build();
    }

}
