package com.electr.electricalconsciousness.domain.repositories;

import com.electr.electricalconsciousness.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findUsuarioByEmail(String email);

    Usuario findUsuarioByToken(String token);
}