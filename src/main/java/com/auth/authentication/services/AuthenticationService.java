package com.auth.authentication.services;

import com.auth.authentication.records.AppUserWrapper;
import com.auth.authentication.records.LoginWrapper;
import com.auth.authentication.records.UniversalResponse;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
 Mono<UniversalResponse>registerUser(AppUserWrapper appUserWrapper);
 Mono<UniversalResponse>login(LoginWrapper loginWrapper);
 Mono<UniversalResponse>logout();
}
