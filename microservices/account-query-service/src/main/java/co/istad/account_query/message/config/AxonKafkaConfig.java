package co.istad.account_query.message.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.StreamableKafkaMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AxonKafkaConfig {

    public static final String PROCESSOR_NAME = "account-group";

    @Autowired
    public void configurationStreamableKafkaSource
            (EventProcessingConfigurer eventProcessingConfigurer,
             StreamableKafkaMessageSource<String, byte[]> stringStreamableKafkaMessageSource){
        log.info("configureStreamableKafkaSource for AxonKafkaConfig");
        eventProcessingConfigurer.registerPooledStreamingEventProcessor(
                PROCESSOR_NAME,
                configuration -> stringStreamableKafkaMessageSource);
    }


}