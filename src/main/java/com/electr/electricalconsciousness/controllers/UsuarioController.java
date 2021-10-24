package com.electr.electricalconsciousness.controllers;

import com.electr.electrical.domain.dto.usuario.UsuarioDTO;
import com.electr.electrical.domain.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> buscarTodosUsuarios() {
        return usuarioService.buscarTodosUsuarios();
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscarUsuarioPorId(@PathVariable Long usuarioId){
        return usuarioService.buscarUsuarioPorId(usuarioId);
    }

    @PostMapping("/salvar-usuario")
    public UsuarioDTO salvarUsuario(@RequestParam("nome") String nome,
                                    @RequestParam("email") String email,
                                    @RequestParam("avatar") MultipartFile avatar,
                                    @RequestParam("roleId") Long roleId,
                                    @RequestParam("password") String password) throws IOException {

        return usuarioService.salvarUsuario(nome, email, avatar, roleId, password);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizarUsuario(@PathVariable Long usuarioId, @RequestParam("nome") String nome,
                                       @RequestParam("email") String email,
                                       @RequestParam("avatar") MultipartFile avatar,
                                       @RequestParam("senha") String senha) {

        return usuarioService.atualizarUsuario(usuarioId, nome, email, avatar, senha);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId){
        return usuarioService.deletarUsuario(usuarioId);
    }
}