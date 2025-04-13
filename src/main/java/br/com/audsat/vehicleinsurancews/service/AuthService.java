package br.com.audsat.vehicleinsurancews.service;

import br.com.audsat.vehicleinsurancews.dto.LoginFormDTO;
import br.com.audsat.vehicleinsurancews.dto.TokenDTO;
import br.com.audsat.vehicleinsurancews.dto.UserDTO;
import br.com.audsat.vehicleinsurancews.exception.BusinessException;
import br.com.audsat.vehicleinsurancews.exception.NotFoundException;
import br.com.audsat.vehicleinsurancews.model.User;
import br.com.audsat.vehicleinsurancews.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtEncoder jwtEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public TokenDTO login(LoginFormDTO dto) {
        User user = userRepository.findByLogin(dto.login())
                .orElseThrow(() -> new BadCredentialsException("User or password is invalid."));
        if (!bCryptPasswordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("User or password is invalid.");
        }

        Instant now = Instant.now();
        Long expiresIn = 720000L; //200 horas de token, apenas para ambiente de testes.

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now).build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters
                .from(claims)).getTokenValue();

        return new TokenDTO(jwtValue, expiresIn);
    }

    @Transactional
    public UserDTO signup(LoginFormDTO dto) {
        if (userRepository.findByLogin(dto.login()).isPresent()) {
            throw new BusinessException("login name already exists");
        }
        User user = userRepository.save(new User(dto.login(), bCryptPasswordEncoder.encode(dto.password())));
        return new UserDTO(user.getId(), user.getLogin());
    }
}
