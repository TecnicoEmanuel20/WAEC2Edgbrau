package pe.edu.cibertec.WAEC2Edgbrau.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Publicacion;
import pe.edu.cibertec.WAEC2Edgbrau.repository.PublicacionRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PublicacionService implements IPublicacionService {

    private PublicacionRepository publicacionRepository;

    @Override
    public List<Publicacion> listadoPublicacion() {
        return publicacionRepository.findAll();
    }

    @Override
    public void guardarPublicacion(Publicacion publicacion) {
        publicacionRepository.save(publicacion);
    }

    @Override
    public Publicacion buscarPublicacion(int id) {
        return publicacionRepository.findById(id).orElse(null);
    }
}
