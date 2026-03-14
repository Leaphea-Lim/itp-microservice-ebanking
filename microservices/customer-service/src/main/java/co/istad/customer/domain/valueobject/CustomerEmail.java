package co.istad.customer.domain.valueobject;

public record CustomerEmail(
    String primaryEmail,
    String secondaryEmail
) {
}
