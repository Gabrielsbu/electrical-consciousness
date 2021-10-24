package com.electr.electricalconsciousness.domain.services;

import com.electr.electricalconsciousness.domain.dto.auth.LoginDTO;
import com.electr.electricalconsciousness.domain.dto.usuario.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthService {

    ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO);
}
