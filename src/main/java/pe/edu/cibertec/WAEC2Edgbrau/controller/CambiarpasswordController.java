package pe.edu.cibertec.WAEC2Edgbrau.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Usuario;
import pe.edu.cibertec.WAEC2Edgbrau.service.IUsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/password")
public class CambiarpasswordController {
    private BCryptPasswordEncoder byencoder;
    private IUsuarioService usuarioService;
    @GetMapping("/cambiar-password")
    public String cambiarPassword(Model model) {
        model.addAttribute("usu", new Usuario());
        return "backoffice/cambiar/password";
    }

    @PostMapping("/cambiar-password")
    public String cambiarPassword(@ModelAttribute("usu") Usuario usuario, Model model) {
        try{
            UserDetails userDetails = (UserDetails)
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String password = byencoder.encode(usuario.getPassword());
            String nomusuario = userDetails.getUsername();
            usuarioService.actualizarUsuario(password, nomusuario);
            model.addAttribute("success", "Cambio de Contraseña Exitosamente");
            model.addAttribute("usu", new Usuario());
        }catch (Exception ex) {
            System.out.println("Error en : " + ex.getMessage());
        }
        return "backoffice/cambiar/password";
    }
}
