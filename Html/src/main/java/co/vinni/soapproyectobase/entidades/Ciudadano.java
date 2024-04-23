

package co.vinni.soapproyectobase.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "CIUDADANOS")
@Entity
public class Ciudadano  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CiUDADANOS")
    @SequenceGenerator(name = "SEQ_CiUDADANOS", sequenceName = "SEQ_CiUDADANOS", allocationSize = 1)
    @Column(name = "CODIGO", nullable = false)
    private long serial;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Numero_Documento")
    private Persona persona;

    @Column(name = "PADRE", nullable = false)
    private String papa;

    @Column(name = "MADRE", nullable = false)
    private String mama;

    @Column(name = "APELLIDO_PADRE")
    private String apellidoPadre;

    @Column(name = "APELLIDO_MADRE")
    private String apellidoMadre;



    public String getPadre() {
        return papa;
    }

    public void setPadre(String papa) {
        this.papa = papa;
    }

    public String getMadre() {
        return mama;
    }

    public void setmadre(String mama) {
        this.mama = mama;
    }

    public String getPadreApellido() {
        return apellidoPadre;
    }

    public void setPadreApellido(String apellidoPadre) {
        this.apellidoPadre = apellidoPadre;
    }

    public String getMadreApellido() {
        return apellidoMadre;
    }

    public void setMadreApellido(String apellidoMadre) {
        this.apellidoMadre = apellidoMadre;
    }

}


