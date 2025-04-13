package br.com.audsat.vehicleinsurancews.controller;

import br.com.audsat.vehicleinsurancews.dto.LoginFormDTO;
import br.com.audsat.vehicleinsurancews.dto.TokenDTO;
import br.com.audsat.vehicleinsurancews.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth Controller", description = "Endpoint para gerar token jwt")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginFormDTO dto){
        return ResponseEntity.ok().body(authService.login(dto));
    }

}
