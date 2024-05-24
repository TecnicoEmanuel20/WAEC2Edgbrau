package pe.edu.cibertec.WAEC2Edgbrau.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import pe.edu.cibertec.WAEC2Edgbrau.model.dto.security.UsuarioSecurity;
import pe.edu.cibertec.WAEC2Edgbrau.service.IUsuarioService;
@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private BCryptPasswordEncoder encoder;
    private IUsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "backoffice/auth/login";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/dashboard";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserDetails userDetails = (UserDetails)
                SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();
        UsuarioSecurity usuarioSecurity =
                (UsuarioSecurity)userDetails;
        session.setAttribute("nomusuario",
                usuarioSecurity.getUsername());
        return "backoffice/auth/home";
    }
    @GetMapping("/registrar")
    public String registraruser(Model model){
        model.addAttribute("u", new Usuario());
        return "backoffice/auth/registros";
    }

    @PostMapping("/registrar")
    public String registraruser(@ModelAttribute Usuario usu, Model model){
        try{
            usu.setPassword(encoder.encode(usu.getPassword()));
            usu.setActivo(true);
            usuarioService.guardarUsuario(usu, usu.getNomusuario());
            model.addAttribute("success", "Usuario Registrado Correctamente");
            model.addAttribute("usuario", new Usuario());
        }catch(Exception ex) {
            System.out.println("Error : " + ex.getCause().getMessage());
        }
        return "backoffice/auth/registros";
    }

}