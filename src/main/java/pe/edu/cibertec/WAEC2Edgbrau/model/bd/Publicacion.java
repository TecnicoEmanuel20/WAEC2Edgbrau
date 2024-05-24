package pe.edu.cibertec.WAEC2Edgbrau.model.bd;

import jakarta.persistence.*;
import lombok.Data;

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
    private Date fechpublicacion;
    private Integer idautor;
    //@ManyToOne
    //    @JoinColumn(name = "idautor")
    //    private Autor autor;
}

