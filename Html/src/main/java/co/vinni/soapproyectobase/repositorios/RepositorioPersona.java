

    package co.vinni.soapproyectobase.repositorios;

    import co.vinni.soapproyectobase.entidades.Persona;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

    import java.util.List;

    public interface RepositorioPersona extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {


        boolean existsByNumeroDoc(long numeroDoc);
    }
