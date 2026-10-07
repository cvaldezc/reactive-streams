package rx.chris.shippingrouter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient quoterClient(@Value("${app.quoter.url}") String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }

    @Bean
    public WebClient courierClient(@Value("${app.courier.url}") String url) {
        return WebClient.builder().baseUrl(url).build();
    }
}
