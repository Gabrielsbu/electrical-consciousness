package com.electr.electricalconsciousness.domain.services.Impl;

import com.electr.electrical.authentication.jwt.JWTConfig;
import com.electr.electrical.domain.dto.auth.LoginDTO;
import com.electr.electrical.domain.dto.usuario.UsuarioDTO;
import com.electr.electrical.domain.models.Usuario;
import com.electr.electrical.domain.models.UsuarioPrincipal;
import com.electr.electrical.domain.repositories.UsuarioRepository;
import com.electr.electrical.domain.services.AuthService;
import com.electr.electrical.domain.utils.UsuarioConverter;
import com.electr.electrical.exceptions.AllException;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final JWTConfig jwtConfig;
    private final UsuarioConverter usuarioConverter;

    private HttpHeaders addTokenInHeaders(UsuarioPrincipal usuarioPrincipal) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", jwtConfig.generateJwtToken(usuarioPrincipal));
        return headers;
    }

    private Usuario findByAuthDetailsLogin(String email) {
        Usuario authIsExist = usuarioRepository.findUsuarioByEmail(email);

        if(authIsExist == null) {
            throw new AllException("Auth dont exist, try again with other username", HttpStatus.NOT_FOUND);
        }

        return authIsExist;
    }

    @Override
    public ResponseEntity<UsuarioDTO> login(LoginDTO loginDTO) {
        Usuario usuarioAuth = findByAuthDetailsLogin(loginDTO.getEmail());
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuarioAuth);

        HttpHeaders jwtHeader = addTokenInHeaders(usuarioPrincipal);

        String token = Objects.requireNonNull(jwtHeader.get("Authorization")).get(0);
        usuarioAuth.setToken(token);

        return new ResponseEntity<>(usuarioConverter.toModelDTO(usuarioRepository.save(usuarioAuth)), jwtHeader, HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String authCredentialsLogin) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(authCredentialsLogin);

        if (usuario == null) {
            throw new AllException("User not found by username: " + authCredentialsLogin, HttpStatus.NOT_FOUND);
        } else {
            usuarioRepository.save(usuario);

            return new UsuarioPrincipal(usuario);
        }
    }
}
