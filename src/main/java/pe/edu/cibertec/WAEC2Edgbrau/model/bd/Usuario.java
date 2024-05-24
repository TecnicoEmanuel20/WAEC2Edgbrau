package pe.edu.cibertec.WAEC2Edgbrau.model.bd;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer idusuario;
    private String nomusuario;
    private String email;
    private String password;
    private String nombres;
    private String apellidos;
    private Boolean activo;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name="idusuario"),
            foreignKey = @ForeignKey(name="user_role_FK1"), inverseJoinColumns = @JoinColumn(name = "idrol"), inverseForeignKey =
    @ForeignKey(name="user_role_FK_2"))
    private Set<Rol> roles;
}


