package co.istad.pipeline.config;
import co.istad.pipeline.client.AccountClient;
import co.istad.pipeline.client.JsonPlaceholderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpInterfaceConfig {


    @Bean
    public JsonPlaceholderClient jsonPlaceholderClient(HttpInterfaceFactory factory) {
        return factory
                .createNormalClient("https://jsonplaceholder.typicode.com",
                        JsonPlaceholderClient.class);
    }

    @Bean
    public AccountClient accountClient(HttpInterfaceFactory factory){
        return factory
                .createLoadBalanceClient("http://account",
                    AccountClient.class);
    }
}


