package com.mchojniak.security.auth;

import java.util.Optional;

interface ApplicationUserRepository {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
