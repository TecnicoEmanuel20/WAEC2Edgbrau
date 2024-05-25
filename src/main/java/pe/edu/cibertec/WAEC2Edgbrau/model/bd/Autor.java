package pe.edu.cibertec.WAEC2Edgbrau.model.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idautor;
    private String nomautor;
    private String apeautor;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechnacautor;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Publicacion> listaPublicacion;
}