package pe.edu.cibertec.WAEC2Edgbrau.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@Data
@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpublicacion;
    private String titulo;
    private String resumen;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechpublicacion;
    private Integer idautor;

    @ManyToOne
    @JoinColumn(name = "idautor", insertable = false, updatable = false)
    private Autor autor;
}

