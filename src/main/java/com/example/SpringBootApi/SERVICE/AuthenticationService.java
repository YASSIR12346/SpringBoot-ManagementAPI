package com.example.SpringBootApi.SERVICE;

import com.example.SpringBootApi.CONFIG.JwtService;
import com.example.SpringBootApi.CONTROLLER.AuthenticationRequest;
import com.example.SpringBootApi.CONTROLLER.AuthenticationResponse;
import com.example.SpringBootApi.CONTROLLER.RegisterRequest;
import com.example.SpringBootApi.CONTROLLER.Response;
import com.example.SpringBootApi.Model.Manager;
import com.example.SpringBootApi.Model.Role;
import com.example.SpringBootApi.REPOSITORY.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ManagerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public Response register(RegisterRequest request) {
        Optional<Manager> userByEmail=repository.findByEmail(request.getEmail());
        if(userByEmail.isPresent()){
            return new Response("Email is already taken");
        }
        var manager = Manager.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        repository.save(manager);
        return new Response(
                "you have been registered Successfully"
              );
    }

    public Response authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch(Exception e){
            return new Response("Invalid User");
        }
        Manager manager = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(manager);
        return new AuthenticationResponse(
                "you are Login Successfully",
                jwtToken);
    }
}
