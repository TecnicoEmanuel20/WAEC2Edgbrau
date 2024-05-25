package pe.edu.cibertec.WAEC2Edgbrau.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.WAEC2Edgbrau.model.bd.Publicacion;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {

}
