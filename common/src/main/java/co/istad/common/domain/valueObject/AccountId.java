package co.istad.common.domain.valueObject;

import org.jspecify.annotations.NonNull;
import java.util.UUID;

public record AccountId(
        UUID value
) {
    @NonNull
    @Override
    public String toString(){
        return value.toString();
    }
}
