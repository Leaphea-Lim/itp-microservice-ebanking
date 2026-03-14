package co.istad.customer.rest;

import co.istad.customer.config.DeadLetterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("dlq")
@RequiredArgsConstructor
public class DqlController {

    private final DeadLetterProcessor deadLetterProcessor;

    @PostMapping("/{processing-group}/any")
    public CompletableFuture<Boolean> processAny(@PathVariable("processing-group") String processingGroup) {
        return deadLetterProcessor.processorAnyFor(processingGroup);
    }
}
