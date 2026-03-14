package co.istad.account.config;

import co.istad.account.infrastructor.interceptor.AccountCommandInterceptor;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AxonConfig {

    private final AccountCommandInterceptor accountCommandInterceptor;

    @Autowired
    public CommandBus commandBus(CommandBus commandBus) {
        commandBus.registerHandlerInterceptor(accountCommandInterceptor);
        return commandBus;
    }
}
