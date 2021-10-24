package com.electr.electricalconsciousness.domain.services;

import com.electr.electricalconsciousness.domain.dto.usuario.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface UsuarioService {

    List<UsuarioDTO> buscarTodosUsuarios();

    UsuarioDTO buscarUsuarioPorId(Long usuarioId);

    UsuarioDTO salvarUsuario(String nome, String email, MultipartFile avatar, Long roleId, String password) throws IOException;

    UsuarioDTO atualizarUsuario(Long usuarioId, String nome, String email, MultipartFile avatar, String senha);

    ResponseEntity<Void> deletarUsuario(Long usuarioId);
}
