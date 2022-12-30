package com.mchojniak.security.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.mchojniak.security.permissions.UserRole.USER_EDIT;
import static com.mchojniak.security.permissions.UserRole.USER_READ;

@Repository("fake")
@RequiredArgsConstructor
class FakeApplicationUserRepositoryService implements ApplicationUserRepository {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                ApplicationUser.builder()
                        .username("userEdit")
                        .password(passwordEncoder.encode("user"))
                        .grantedAuthorities(USER_EDIT.getGrantedAuthorities())
                        .isAccountNonExpired(true)
                        .isCredentialsNonExpired(true)
                        .isAccountNonLocked(true)
                        .isEnabled(true)
                        .build(),
                ApplicationUser.builder()
                        .username("userRead")
                        .password(passwordEncoder.encode("user"))
                        .grantedAuthorities(USER_READ.getGrantedAuthorities())
                        .isAccountNonExpired(true)
                        .isCredentialsNonExpired(true)
                        .isAccountNonLocked(true)
                        .isEnabled(true)
                        .build()
        );
    }
}
