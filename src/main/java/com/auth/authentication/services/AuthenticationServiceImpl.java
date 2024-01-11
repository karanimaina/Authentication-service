package com.auth.authentication.services;

import com.auth.authentication.exceptions.UserException;
import com.auth.authentication.model.AppUser;
import com.auth.authentication.records.AppUserWrapper;
import com.auth.authentication.records.UniversalResponse;
import com.auth.authentication.repo.UserRepository;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public Mono<UniversalResponse> registerUser(AppUserWrapper appUserWrapper) {
        return Mono.fromCallable(() -> {
            Optional<AppUser> user = userRepository.findByEmail(appUserWrapper.email());
            if (user.isPresent()) {
                throw new UserException("email already registered, proceed to login");
            }
            AppUser appUser = AppUser
                    .builder()
                    .email(appUserWrapper.email())
                    .firstName(appUserWrapper.firstName())
                    .lastName(appUserWrapper.lastName())
                    .password(passwordEncoder.encode(appUserWrapper.password()))
                    .build();
            userRepository.save(appUser);
            return UniversalResponse.builder()
                    .message("user registered successfully")
                    .status(200)
                    .build();
        }).publishOn(Schedulers.boundedElastic());
    }



    @Override
    public Mono<UniversalResponse> login() {
        return null;
    }

    @Override
    public Mono<UniversalResponse> logout() {
        return null;
    }
}
