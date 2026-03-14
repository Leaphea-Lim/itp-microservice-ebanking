package co.istad.account.infrastructor.interceptor;

import co.istad.account.domain.command.CreateAccountCommand;
import co.istad.account.infrastructor.client.CustomerClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountCommandInterceptor implements MessageHandlerInterceptor<CommandMessage<?>> {

    private final CustomerClient customerClient;

    @Override
    public Object handle(UnitOfWork<? extends CommandMessage<?>> unitOfWork, InterceptorChain interceptorChain) throws Exception {

        CommandMessage<?> commandMessage = unitOfWork.getMessage();
        log.info("Intercepting command: {}", commandMessage.getPayload());

        if (commandMessage.getPayload() instanceof CreateAccountCommand createAccountCommand) {
            log.info("Validating customerId: {}", createAccountCommand.customerId());

            try {
                customerClient.getCustomerById(createAccountCommand.customerId().getValue());
                log.info("Customer validated successfully");
            }
            catch (WebClientResponseException.NotFound e){
                //customer service return 422
                log.error("Customer not found: {}", createAccountCommand.customerId().getValue());
                throw new ResponseStatusException(
                       HttpStatusCode.valueOf(422),
                        "Customer with id: " +
                                createAccountCommand.customerId().getValue() + "not found");
            }
            catch (WebClientResponseException e){
                // customer service returned other error (500, 403, etc.)
                log.error("Customer service error: {}", e.getStatusCode());
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                        "Customer service error: " + e.getStatusCode());

            }
            catch (Exception e){
                //network error, timeout...
                log.error("Cannot reach customer service: {}", e.getMessage());
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                        "Customer Service is unavailable");
            }
        }

        return interceptorChain.proceed();
    }
}
