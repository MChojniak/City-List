package com.mchojniak.security.permissions;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.mchojniak.security.permissions.UserPermission.ALLOW_EDIT;
import static com.mchojniak.security.permissions.UserPermission.ALLOW_READ;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    USER_EDIT(Sets.newHashSet(ALLOW_EDIT)),
    USER_READ(Sets.newHashSet(ALLOW_READ));
    
    private final Set<UserPermission> permissions;

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
