package edu.icet.service;

import edu.icet.config.JWTService;
import edu.icet.dto.AuthResponse;
import edu.icet.dto.RegisterRequest;
import edu.icet.entity.User;
import edu.icet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
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
}
