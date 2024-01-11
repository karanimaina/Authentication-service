package com.auth.authentication.records;

import lombok.NonNull;

public record AppUserWrapper (
         @NonNull
         String firstName,
         @NonNull
         String lastName,
         @NonNull
         String email,
         @NonNull
         String password
){
}
