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

    @Override
    public Usuario obtenerUsuarioxNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario, String nombreRol) {
        Rol usuarioRol = rolRepository.findByNomrol(nombreRol);
        if(usuarioRol == null) {
            usuarioRol = new Rol();
            usuarioRol.setNomrol(nombreRol);
            rolRepository.save(usuarioRol);
        }
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void actualizarUsuario(String pasword, String nomusuario) {
        usuarioRepository.updateUser(pasword,nomusuario);
    }

}
