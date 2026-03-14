package istad.co.identity.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUserDetails extends User {

    private String uuid;
    private String email;
    private String familyName;
    private String givenName;
    private String phoneNumber;
    private String profileImage;
    private String gender;

    public CustomUserDetails(String username,
                             String password,
                             boolean enabled,
                             boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities,
                             String uuid,
                             String email,
                             String familyName,
                             String givenName,
                             String phoneNumber,
                             String profileImage,
                             String gender) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.uuid = uuid;
        this.email = email;
        this.familyName = familyName;
        this.givenName = givenName;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.gender = gender;
    }
}
