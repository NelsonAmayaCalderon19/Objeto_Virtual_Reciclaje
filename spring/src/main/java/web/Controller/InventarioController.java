package web.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.repository.*;
import web.entities.*;


@Controller
//@RestController
@RequestMapping({"/inventario"})
public class InventarioController {
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
	@Autowired
	InventarioRepository inventariorepository;
	
	@GetMapping("/ver")
	public String listado(Model model, Model mod){	
		mod.addAttribute("computador", computadorrepository.findAll());
		model.addAttribute("inventario", inventariorepository.findAll());
		return "inventario";
	}
	
	@PostMapping({ "/actualizar" })
    public String actualizarinv(HttpServletRequest request, Model model) {
		String id = request.getParameter("idpc");
		Optional<Inventario> inventarioOpt = inventariorepository.findById(Integer.parseInt(id));
		if(!inventarioOpt.isPresent()) {
			return "inventario";
		}else {
		Inventario e = inventarioOpt.get();
		model.addAttribute("invent", e);

		return "actualizarinv";
		}

    }
	
	@GetMapping({"/editar"})
    public ModelAndView cargar(HttpServletRequest request) {		
		//List<Cliente> categorias = clienteRepository.findAll();
		Optional<Inventario> inventarioOpt = inventariorepository.findById(Integer.parseInt(request.getParameter("id")));
		 Inventario inventario = inventarioOpt.get();		
		
		ModelAndView modelAndView = new ModelAndView();

	    modelAndView.setViewName("registro");
	    modelAndView.addObject("inventario", inventario);
		    
		    
	   /* modelAndView.addObject("categorias", categoriaRepository.findAll());
	    modelAndView.addObject("tipos", tipoRepository.findAll());
	    modelAndView.addObject("asignaturas", asignaturaRepository.findAll());
	    modelAndView.addObject("eventos", eventoRepository.findAll());
		
	    modelAndView.addObject("enlace", "/user/editar/" + proyecto.getId());
	    modelAndView.addObject("btn", "Actualizar");*/	
		return modelAndView;

    }
	@PostMapping({"/actualizado"})
    public String  editar(Model model, Model mod, HttpServletRequest request,@ModelAttribute("inventario") @Validated (Inventario.class) Inventario inventario, RedirectAttributes redirectAttrs,BindingResult bindingResult) throws Exception {
       Optional<Computador> mOptional = computadorrepository.findById(Integer.parseInt(request.getParameter("txtid")));

        Computador c = mOptional.get();
        
		Optional<Inventario> inventarioOpt = inventariorepository.findById(Integer.parseInt(request.getParameter("txtid")));
		Inventario inventarioAct = inventarioOpt.get();
		System.out.println(inventarioAct.getIdComputador());
		System.out.println(inventarioAct.getCantidad());
		inventario.setIdComputador(inventarioAct.getIdComputador());
		inventario.setComputador(c);
		inventario.setCantidad(inventarioAct.getCantidad()+Integer.parseInt(request.getParameter("txtcantidad")));
		inventariorepository.save(inventario);

		redirectAttrs
        .addFlashAttribute("mensaje", "Inventario actualizado correctamente")
        .addFlashAttribute("clase", "success");
		mod.addAttribute("computador", computadorrepository.findAll());
		model.addAttribute("inventario", inventariorepository.findAll());
		return "inventario";
		
	}
	


}
