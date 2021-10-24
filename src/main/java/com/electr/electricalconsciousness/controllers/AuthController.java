package com.electr.electricalconsciousness.controllers;

import com.electr.electricalconsciousness.domain.dto.auth.LoginDTO;
import com.electr.electricalconsciousness.domain.dto.usuario.UsuarioDTO;
import com.electr.electricalconsciousness.domain.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authentication")
    public ResponseEntity<UsuarioDTO> signIn(@RequestBody LoginDTO loginDTO) {
        authenticate(loginDTO.getEmail(), loginDTO.getSenha());
        return authService.login(loginDTO);
    }

    public void authenticate(String email, String senha) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
    }
}
