package pe.edu.cibertec.WAEC2Edgbrau.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Autor;
import pe.edu.cibertec.WAEC2Edgbrau.repository.AutorRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class AutorService implements IAutorService{

    private AutorRepository autorRepository;

    @Override
    public List<Autor> listadoAutor() {
        return autorRepository.findAll();
    }
}
