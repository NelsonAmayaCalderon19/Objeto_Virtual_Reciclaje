package web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import web.entities.Empleado;
import web.repository.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
@Controller
//@RestController
@RequestMapping({""})
public class UserController {

	@Autowired
	EmpleadoRepository empleadorepository;

	@Autowired
	ComputadorRepository computadorrepository;
	
	@GetMapping("/hola")
	public String index(){
		return "Hola Mundo con Spring Boot por GET";
	}
	
	@GetMapping("/adios")
	public String adios(){
		
		Optional<Empleado> empleadoOpt = empleadorepository.findById(17374);
		
		Empleado empleado = empleadoOpt.get();
		return "Adios con Spring Boot por GET "+ empleado.getNombre();
	}
	
	@GetMapping("/")
	public String vista(Model modelo){	
		modelo.addAttribute("empleado", empleadorepository.findAll());

		return "index";
	}
	
	@GetMapping("/get/{codigo}")
	public String ver(@PathVariable Integer codigo){
		
		Optional<Empleado> clienteOpt = empleadorepository.findById(codigo);
		if(!clienteOpt.isPresent()) {
			return "El Código "+codigo+" de Cliente - No Existe";
		}else {
		Empleado cliente = clienteOpt.get();
		return "Adios con Spring Boot por GET "+ cliente.getNombre();
		}
	}
	
	@PostMapping("/hola")
	public String post(){
		return "Hola Mundo con Spring Boot por POST";
	}
	
	@PutMapping("/hola")
	public String put(){
		return "Hola Mundo con Spring Boot por PUT";
	}
	@PostMapping({"/home"})
    public String home(HttpServletRequest request, Model modelo, Model imprimir) {
		Optional<Empleado> empleadoOpt = empleadorepository.findById(Integer.parseInt(request.getParameter("cedula")));
		if(!empleadoOpt.isPresent()) {
			return "index";
		}else {
		Empleado empleado = empleadoOpt.get();
		if(empleado.getClave().equalsIgnoreCase(request.getParameter("clave"))){
			modelo.addAttribute("empleado", empleado.getNombre());
			imprimir.addAttribute("computador", computadorrepository.findAll());
		return "home";
		
	}else {
		return "index";
		}
	}
		       
    }
	
	@GetMapping("/listado")
	public String listado(Model modelo, Model model){	
		modelo.addAttribute("computador", computadorrepository.findAll());
		model.addAttribute("empleado", empleadorepository.findAll());
		return "home";
	}
	
	@GetMapping("/registrar")
	public String registrar(){	
		return "registrarpc";
	}
	
	@GetMapping({"/iniciar"})
    public String iniciarSesion() {
        return "home";
    }
	@PostMapping({"/salir"})
    public String cerrarSesion() {
        return "index";
    }
}
