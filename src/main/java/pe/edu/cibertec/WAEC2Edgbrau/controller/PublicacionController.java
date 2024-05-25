package pe.edu.cibertec.WAEC2Edgbrau.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Autor;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Publicacion;
import pe.edu.cibertec.WAEC2Edgbrau.model.dto.response.RespuestaResponse;
import pe.edu.cibertec.WAEC2Edgbrau.service.IAutorService;
import pe.edu.cibertec.WAEC2Edgbrau.service.IPublicacionService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/publicacion")
public class PublicacionController {

    private IPublicacionService publicacionService;
    private IAutorService autorService;

    @GetMapping(value = "/init")
    public String publicacion(Model model) {
        model.addAttribute("p",publicacionService.listadoPublicacion());
        return "backoffice/publicacion/frmpublicacion";
    }


}
