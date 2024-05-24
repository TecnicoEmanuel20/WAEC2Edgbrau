package pe.edu.cibertec.WAEC2Edgbrau.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Rol;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Usuario;
import pe.edu.cibertec.WAEC2Edgbrau.repository.RolRepository;
import pe.edu.cibertec.WAEC2Edgbrau.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();
    @Override
    public Usuario obtenerUsuarioxNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder
                .encode("123456"));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }
    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioxId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElse(null);
        usuario.setPassword("");
        return usuario;
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioRepository.updateUser(
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getActivo(),
                usuario.getIdusuario()
        );

    }
}
