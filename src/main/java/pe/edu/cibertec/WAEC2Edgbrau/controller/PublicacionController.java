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

    @GetMapping(value = "/list")
    @ResponseBody
    public List<Publicacion> listadoPublicacion() {
        return publicacionService.listadoPublicacion();
    }

    @PostMapping(value = "/registrar")
    @ResponseBody
    public RespuestaResponse guardarPublicacion(@RequestBody Publicacion publicacion) {
        boolean rs = true;
        String men = "Producto Registrado";
        try{
            if(publicacion.getIdpublicacion() > 0) {
                publicacionService.guardarPublicacion(publicacion);
            }else {
                publicacionService.guardarPublicacion(publicacion);
            }
        }catch (Exception e) {
            rs = false;
            men = "Error en : " + e.getCause().getMessage();
        }
        return RespuestaResponse.builder().mensaje(men).resultado(rs).build();
    }

    @GetMapping(value = "/{idpublicacion}")
    @ResponseBody
    public Publicacion buscarPublicacion(@PathVariable Integer idpublicacion) {
        return publicacionService.buscarPublicacion(idpublicacion);
    }

    @GetMapping(value = "/autor")
    @ResponseBody
    public List<Autor> listadoAutor() {
        return autorService.listadoAutor();
    }
}
