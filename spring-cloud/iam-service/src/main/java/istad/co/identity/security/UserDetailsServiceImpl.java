package istad.co.identity.security;

import istad.co.identity.domain.User;
import istad.co.identity.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


/*
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load or find user from database
//        istad.co.identity.domain.User loggedInUser = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username));

        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        // Build UserDetails object
        String[] roles = loggedInUser.getRoles().stream().map(Role::getName).toArray(String[]::new);
        // Important for user authorities
        List<GrantedAuthority> authorities = new ArrayList<>();

        loggedInUser.getRoles().forEach(role -> {
            // Add role into authority
            // authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));UserDetails userSecurity = org.springframework.security.core.userdetails.User.builder()
            role.getPermissions().forEach(permission -> {
                // Add permission into authority
                //.getName
                authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            });
        });

        UserDetails userSecurity = org.springframework.security.core.userdetails.User.builder()
                .username(loggedInUser.getUsername())
                .password(loggedInUser.getPassword())
                .authorities(authorities)
                .build();

        log.info("UserDetailsServiceImpl loadUserByUsername = {}", userSecurity.getAuthorities());
        log.info("UserDetailsServiceImpl loadUserByUsername = {}", userSecurity.getUsername());

        return userSecurity;

    }
}
*/

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userLogged = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        // Collect authorities (roles and permissions)
        List<GrantedAuthority> authorities = new ArrayList<>();
        userLogged.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });

        // Build CustomUserDetails with additional user information
        CustomUserDetails customUserDetails = new CustomUserDetails(
                userLogged.getUsername(),
                userLogged.getPassword(),
                userLogged.getIsEnabled(),
                userLogged.getAccountNonExpired(),
                userLogged.getCredentialsNonExpired(),
                userLogged.getAccountNonLocked(),
                authorities,
                userLogged.getUuid(),
                userLogged.getEmail(),
                userLogged.getFamilyName(),
                userLogged.getGivenName(),
                userLogged.getPhoneNumber(),
                userLogged.getProfileImage(),
                userLogged.getGender()
        );

        log.info("CustomUserDetails loaded for user: {}", customUserDetails.getUsername());
        log.info("User authorities: {}", customUserDetails.getAuthorities());

        return customUserDetails;
    }
}
