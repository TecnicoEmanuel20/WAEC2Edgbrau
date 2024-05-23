package pe.edu.cibertec.WAEC2Edgbrau.service;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Usuario;

import java.util.List;

public interface IUsuarioService {

    Usuario obtenerUsuarioxNomUsuario(String nomusuario);
    Usuario guardarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario obtenerUsuarioxId(Integer id);
    void actualizarUsuario(Usuario usuario);

}
