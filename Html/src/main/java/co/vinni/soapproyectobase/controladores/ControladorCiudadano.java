package co.vinni.soapproyectobase.controladores;

import co.vinni.soapproyectobase.dto.CiudadanoDto;
import co.vinni.soapproyectobase.servicios.ServicioCiudadano;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Log4j2
@Controller
public class ControladorCiudadano {

    private static final Logger logger = LogManager.getLogger(ControladorCiudadano.class);

    @Autowired
    ServicioCiudadano servicioCiudadanos;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping({"/ciudadanos"})
    public String listarCiudadanos(Model model) {
        logger.info("Verificando ");
        model.addAttribute("ciudadanos",  servicioCiudadanos.obtenerCiudadanos());
        return "ciudadanos";
    }

    @GetMapping("/ciudadanos/nuevo")
    public String mostrarFormularioNuevoCiudadano(Model model) {
        CiudadanoDto ciudadanoDto = new CiudadanoDto();
        model.addAttribute("ciudadano", ciudadanoDto);
        return "crear_ciudadano";
    }

    @PostMapping("/ciudadanos")
    public String registrarCiudadano(@ModelAttribute("ciudadano") CiudadanoDto ciudadano) {
        servicioCiudadanos.registrarCiudadano(ciudadano);
        return "redirect:/ciudadanos";
    }

    @GetMapping("/ciudadanos/modificar/{serial}")
    public String mostrarFormularioEditarCiudadano(@PathVariable long serial, Model model) {
        CiudadanoDto ciudadanoDto = new CiudadanoDto();
        model.addAttribute("ciudadano", servicioCiudadanos.obtenerCiudadanosPorserial(serial));
        return "editar_ciudadano";
    }

    @PostMapping("/ciudadanos/{serial}")
    public String modificarCiudadano(@PathVariable long serial, @ModelAttribute("ciudadano") CiudadanoDto ciudadanoDto, Model model) {
        model.addAttribute("ciudadano", servicioCiudadanos.modificarCiudadano(ciudadanoDto));
        return "redirect:/ciudadanos";
    }

    @GetMapping("/ciudadanos/{serial}")
    public String eliminarCiudadano(@PathVariable long serial) {
        servicioCiudadanos.eliminarCiudadano(serial);
        return "redirect:/ciudadanos";
    }


}
