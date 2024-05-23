package pe.edu.cibertec.WAEC2Edgbrau.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.WAEC2Edgbrau.model.dto.security.UsuarioSecurity;

@Controller
@RequestMapping("/auth")
public class LoginController {

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
                usuarioSecurity.getEmail());
        return "backoffice/auth/home";
    }
}