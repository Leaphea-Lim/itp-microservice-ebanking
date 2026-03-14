package co.istad.account.infrastructor.config;

import co.istad.account.infrastructor.client.CustomerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpInterfaceConfig {

    @Bean
    public CustomerClient customerClient(HttpInterfaceFactory factory){
        return factory
                .createLoadBalanceClient("http://customer",
                    CustomerClient.class);
    }
}


