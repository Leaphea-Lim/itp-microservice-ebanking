package istad.co.identity.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        Authentication principal = context.getPrincipal();

        if (!(principal.getPrincipal() instanceof CustomUserDetails userDetails)) {
            return;
        }

        // Add custom claims to ID Token
        if (OidcParameterNames.ID_TOKEN.equals(context.getTokenType().getValue())) {
            log.info("Adding custom claims to ID token for user: {}", userDetails.getUsername());

            context.getClaims()
                    .claim("uuid", userDetails.getUuid())
                    .claim("email", userDetails.getEmail())
                    .claim("family_name", userDetails.getFamilyName())
                    .claim("given_name", userDetails.getGivenName())
                    .claim("name", userDetails.getGivenName() + " " + userDetails.getFamilyName())
                    .claim("phone_number", userDetails.getPhoneNumber())
                    .claim("profile_image", userDetails.getProfileImage())
                    .claim("gender", userDetails.getGender())
                    .claim("authorities", userDetails.getAuthorities().stream()
                            .map(a -> a.getAuthority())
                            .collect(Collectors.toList()));
        }
    }
}
