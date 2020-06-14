package web.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import web.entities.*;
import web.repository.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
@Controller
//@RestController
@RequestMapping({"/compu"})
public class CompuController {
	@Autowired
	ComputadorRepository computadorrepository;
	@Autowired
	EmpleadoRepository empleadorepository;
	@Autowired
	MarcaRepository marcarepository;
	@Autowired
	TipocompRepository tipocomprepository;
	@Autowired
	InventarioRepository inventariorepository;
	@GetMapping("/listado")
	public String listado(Model modelo, Model model){	
		modelo.addAttribute("computador", computadorrepository.findAll());
		model.addAttribute("empleado", empleadorepository.findAll());
		return "home";
	}
	
	@GetMapping("/registrar")
	public String registrar(Model modelo){
		modelo.addAttribute("computador", new Computador());
		modelo.addAttribute("marcas", marcarepository.findAll());
		//modelo.addAttribute("countryName ", "nelson");
		return "registrarpc";
	}
	
	@PostMapping({ "/registrarComputadora" })
    public String registrarUsuario(HttpServletRequest request, Model modelo) {


		Inventario inv = new Inventario();
        Computador u = new Computador();
        Optional<Marca> mOptional = marcarepository.findById(Integer.parseInt(request.getParameter("selectmarca")));

        Marca c = mOptional.get();
        
        Optional<Tipocomp> tOptional = tipocomprepository.findById(Integer.parseInt(request.getParameter("selecttipo")));

        Tipocomp tp = tOptional.get();
        String descripcion = request.getParameter("txtdescripcion");
        Double precio = Double.parseDouble(request.getParameter("txtprecio"));
        String imagen = request.getParameter("txtimagen");

        
        u.setDescripcion(descripcion);        
		u.setMarcaBean(c);
		u.setTipocomp(tp);
		u.setPrecio(precio);
		u.setImagen(imagen);
        System.out.println(u);
        computadorrepository.save(u);
        inv.setIdComputador(u.getId());
        inv.setCantidad(0);
        inventariorepository.save(inv);
        modelo.addAttribute("computador", computadorrepository.findAll());
        return "home";
    }
}
