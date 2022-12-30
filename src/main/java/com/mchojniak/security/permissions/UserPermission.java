package com.mchojniak.security.permissions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserPermission {
    ALLOW_EDIT("user:edit"),
    ALLOW_READ("user:read");

    private final String permission;
}
