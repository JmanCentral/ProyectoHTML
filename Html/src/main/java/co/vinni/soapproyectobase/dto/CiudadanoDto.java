package co.vinni.soapproyectobase.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null; // Importar la anotación Null
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CiudadanoDto implements Serializable {

    private long serial;
    private String papa;
    private String mama;
    private long numeroDoc;
    private String nombre;
    private Date fechanac;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private String tipoDoc;
    private int edad;
    @Null(message = "El apellido del padre puede ser nulo")
    private String apellidoPadre;
    @Null(message = "El apellido de la madre puede ser nulo")
    private String apellidoMadre;
}


