package web.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.repository.*;
import web.entities.*;
@Controller
//@RestController
@RequestMapping({"/empleado"})
public class EmpleadoController {
	@Autowired
	ComputadorRepository computadorrepository;
	@Autowired
	EmpleadoRepository empleadorepository;
	@Autowired
	MarcaRepository marcarepository;
	@Autowired
	TipocompRepository tipocomprepository;
	@Autowired
	ClienteRepository clienterepository;
	
	@GetMapping("/listado")
	public String listado(Model model, Model mod){	
		model.addAttribute("empleado", empleadorepository.findAll());
		mod.addAttribute("computador", computadorrepository.findAll());
		return "empleado";
	}
	
	@GetMapping("/delete/{codigo}")
	public String ver(@PathVariable Integer codigo, Model model, Model mod){
		
		Optional<Empleado> empleadoOpt = empleadorepository.findById(codigo);
		if(!empleadoOpt.isPresent()) {
			return "empleado";
		}else {
		Empleado e = empleadoOpt.get();
		empleadorepository.delete(e);
		model.addAttribute("empleado", empleadorepository.findAll());
		mod.addAttribute("computador", computadorrepository.findAll());
		return "empleado";
		}
	}
	
	@GetMapping("/registrar")
	public String registrar(Model modelo){	
		return "registraremp";
	}
	
	@PostMapping({ "/registrarEmpleado" })
    public String registrarUsuario(HttpServletRequest request, Model model) {

		Empleado u = new Empleado();
        String cedula =  request.getParameter("txtcedula");
        String nombre = request.getParameter("txtnombre");
        String telefono = request.getParameter("txttelefono");
        String cargo = request.getParameter("selectcargo");
        String clave = request.getParameter("txtclave");
   
        u.setCedula(Integer.parseInt(cedula));
        u.setNombre(nombre);
        u.setTelefono(telefono);
        u.setCargo(cargo);
        u.setClave(clave);
        System.out.println(u);
        empleadorepository.save(u);
        model.addAttribute("computador", computadorrepository.findAll());
        return "home";
    }
}
