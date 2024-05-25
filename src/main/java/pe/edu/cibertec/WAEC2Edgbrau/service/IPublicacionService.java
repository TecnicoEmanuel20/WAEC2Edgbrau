package pe.edu.cibertec.WAEC2Edgbrau.service;

import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Publicacion;

import java.util.List;

public interface IPublicacionService {
    List<Publicacion> listadoPublicacion();
    void guardarPublicacion(Publicacion publicacion);
    Publicacion buscarPublicacion(int id);
}
