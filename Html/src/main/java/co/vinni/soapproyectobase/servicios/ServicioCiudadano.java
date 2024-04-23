package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.CiudadanoDto;
import co.vinni.soapproyectobase.entidades.Ciudadano;
import co.vinni.soapproyectobase.entidades.Persona;
import co.vinni.soapproyectobase.repositorios.RepositorioCiudadano;
import co.vinni.soapproyectobase.repositorios.RepositorioPersona;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServicioCiudadano implements Serializable {

    private ModelMapper modelMapper;

    private final RepositorioCiudadano repoCiudadano;
    private final RepositorioPersona repoPersona;

    public CiudadanoDto registrarCiudadano(CiudadanoDto ciudadanoDto) {

        if (repoPersona.existsByNumeroDoc(ciudadanoDto.getNumeroDoc())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de documento ya está en uso");
        }

        Ciudadano ciudadano = modelMapper.map(ciudadanoDto, Ciudadano.class);
        Persona persona = modelMapper.map(ciudadanoDto, Persona.class);
        Persona nuevaPersona = repoPersona.save(persona);
        ciudadano.setPersona(nuevaPersona);

        try {
            Ciudadano nuevoCiudadano = repoCiudadano.save(ciudadano);
            return modelMapper.map(nuevoCiudadano, CiudadanoDto.class);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de documento ya está en uso");
        }
    }

    public List<CiudadanoDto> obtenerCiudadanos() {
        List<Ciudadano> ciudadanos = repoCiudadano.findAll();
        List<CiudadanoDto> ciudadanosDto = new ArrayList<>();

        for (Ciudadano ciudadano : ciudadanos) {
            CiudadanoDto ciudadanoDto = modelMapper.map(ciudadano, CiudadanoDto.class);
            Persona persona = ciudadano.getPersona();
            ciudadanoDto.setNombre(persona.getNombre());
            ciudadanoDto.setNumeroDoc(persona.getnumeroDoc());
            ciudadanoDto.setFechanac(persona.getFechanac());
            ciudadanoDto.setTipoDoc(persona.getTipodoc());
            ciudadanoDto.setEdad(persona.getEdad());

            ciudadanosDto.add(ciudadanoDto);
        }

        return ciudadanosDto;
    }

    public CiudadanoDto obtenerCiudadanosPorserial(long serial) {
        Ciudadano ciudadano = repoCiudadano.getReferenceById(serial);
        CiudadanoDto ciudadanoDto = modelMapper.map(ciudadano, CiudadanoDto.class);
        Persona persona = ciudadano.getPersona();
        ciudadanoDto.setNombre(persona.getNombre());
        ciudadanoDto.setNumeroDoc(persona.getnumeroDoc());
        ciudadanoDto.setFechanac(persona.getFechanac());
        ciudadanoDto.setTipoDoc(persona.getTipodoc());
        ciudadanoDto.setEdad(persona.getEdad());
        return ciudadanoDto;
    }




    public CiudadanoDto modificarCiudadano(CiudadanoDto ciudadanoDto) {

        Ciudadano ciudadano = modelMapper.map(ciudadanoDto, Ciudadano.class);

        Persona persona = modelMapper.map(ciudadanoDto, Persona.class);

        Persona nuevaPersona = repoPersona.save(persona);

        ciudadano.setPersona(nuevaPersona);

        Ciudadano ciudadanoModificado = repoCiudadano.save(ciudadano);

        return modelMapper.map(ciudadanoModificado, CiudadanoDto.class);
    }

    public void eliminarCiudadano(long serial) {

        repoCiudadano.deleteById(serial);
    }

}
