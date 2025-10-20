package edu.icet.service;

import edu.icet.config.JWTService;
import edu.icet.dto.AuthResponse;
import edu.icet.dto.LoginRequest;
import edu.icet.dto.RegisterRequest;
import edu.icet.entity.User;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(registerRequest.getRoles())
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail(), user.getRoles());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail() ,loginRequest.getPassword()));

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user.getEmail(), user.getRoles());
        return new AuthResponse(token);

    }
}
