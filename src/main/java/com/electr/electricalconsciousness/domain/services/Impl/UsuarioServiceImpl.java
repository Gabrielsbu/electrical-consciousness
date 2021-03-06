package com.electr.electricalconsciousness.domain.services.Impl;

import com.electr.electricalconsciousness.domain.dto.usuario.UsuarioDTO;
import com.electr.electricalconsciousness.domain.enums.StatusUser;
import com.electr.electricalconsciousness.domain.models.MediaPicture;
import com.electr.electricalconsciousness.domain.models.Role;
import com.electr.electricalconsciousness.domain.models.Usuario;
import com.electr.electricalconsciousness.domain.repositories.MediaPictureRepository;
import com.electr.electricalconsciousness.domain.repositories.RoleRepository;
import com.electr.electricalconsciousness.domain.repositories.UsuarioRepository;
import com.electr.electricalconsciousness.domain.services.AWSService;
import com.electr.electricalconsciousness.domain.services.UsuarioService;
import com.electr.electricalconsciousness.domain.utils.UsuarioConverter;
import com.electr.electricalconsciousness.exceptions.AllException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final RoleRepository roleRepository;
    private final AWSService awsService;
    private final MediaPictureRepository mediaPictureRepository;

    public String gerarSenhaCodificada(String senha) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.encode(senha);
    }

    private void verificacaoEmailExistente(String email) {
        Usuario usuarioExistent = usuarioRepository.findUsuarioByEmail(email);

        if(usuarioExistent != null) {
            throw new AllException("J?? existe um usu??rio com este email cadastrado, tente novamente com outro email", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UsuarioDTO> buscarTodosUsuarios(){
        List<UsuarioDTO> usuarios = usuarioConverter.toCollectionDTO(usuarioRepository.findAll());

        if(usuarios.isEmpty()){
            throw new AllException("A lista de usu??rios se encontra vazia", HttpStatus.NO_CONTENT);
        }

        return usuarios;
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(Long usuarioId){
        return usuarioConverter.toModelDTO(usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new AllException("Usu??rio n??o encontrado", HttpStatus.NOT_FOUND)));
    }

    @Override
    public UsuarioDTO salvarUsuario(String nome, String email, MultipartFile avatar, Long roleId, String password) throws IOException {
        verificacaoEmailExistente(email);

        Role roleExistent = roleRepository.findById(roleId)
                .orElseThrow(() -> new AllException("Role not found", HttpStatus.NOT_FOUND));

        Long awsId = awsService.saveMediaInS3(avatar);

        MediaPicture profilePicture = mediaPictureRepository.findById(awsId)
                .orElseThrow(() -> new AllException("Imagem de perfil n??o encontrada", HttpStatus.NOT_FOUND));

        String senhaCriptografada = gerarSenhaCodificada(password);

        Usuario usuario = Usuario.builder().setNome(nome).setEmail(email).setSenha(senhaCriptografada).setMediaPicture(profilePicture).setStatus(StatusUser.ACTIVE)
                .setRole(roleExistent).build();

        return usuarioConverter.toModelDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO atualizarUsuario(Long usuarioId, String nome, String email, MultipartFile avatar, String senha){
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new AllException("Usuario n??o encontrado", HttpStatus.NOT_FOUND));

        verificacaoEmailExistente(email);

        String senhaCodificada = gerarSenhaCodificada(senha);

        if(nome != null) {
            usuarioExistente.setNome(nome);
        }

        if(email != null) {
            usuarioExistente.setEmail(email);
        }

        if(senha != null) {
            usuarioExistente.setSenha(senhaCodificada);
        }

        return usuarioConverter.toModelDTO(usuarioRepository.save(usuarioExistente));
    }


    @Override
    public ResponseEntity<Void> deletarUsuario(Long usuarioId){

        UsuarioDTO usuario = buscarUsuarioPorId(usuarioId);

        if( usuario == null ) {
            throw new AllException("N??o existe usu??rio com este identificador");
        }

        usuarioRepository.deleteById(usuarioId);

        return ResponseEntity.noContent().build();

    }
}
