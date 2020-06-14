package web.Controller;

import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.repository.*;
import web.util.SendMailService;
import web.entities.*;


@Controller
//@RestController
@RequestMapping({"/cliente"})
public class ClienteController {
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
	SendMailService sendMailService;
	
	@GetMapping("/listado")
	public String listado(Model modelo, Model model, Model mod){	
		modelo.addAttribute("cliente", clienterepository.findAll());
		model.addAttribute("empleado", empleadorepository.findAll());
		mod.addAttribute("computador", computadorrepository.findAll());
		return "cliente";
	}
	@GetMapping("/registrar")
	public String registrar(Model modelo){	
		return "registrarcl";
	}
	@PostMapping({ "/registrarCliente" })
    public String registrarUsuario(HttpServletRequest request, Model model) {

		Cliente u = new Cliente();

        String cedula = request.getParameter("txtcedula");
        String nombre = request.getParameter("txtnombre");
        String telefono = request.getParameter("txttelefono");
        String email = request.getParameter("txtemail");

    
        u.setCedula(cedula);
        u.setNombre(nombre);
        u.setTelefono(telefono);
        u.setEmail(email);
        System.out.println(u);
        clienterepository.save(u);
        String asunto = "Bienvenido A la Familia - CompuWeb Cúcuta"; 
        String mensaje = "Bienvenido: "+u.getNombre()+",\nNuestras tiendas estarán disponible para ti, por este medio \nContactanos a nuestro telefono: 31272632637 \ny Realiza tus pedidos via telefonica o a nuestro correo: compuwebcucuta@gmail.com";
      
        sendMailService.sendMail("nelsonamayacalderon@gmail.com", u.getEmail(), asunto, mensaje);
        model.addAttribute("computador", computadorrepository.findAll());
        return "home";
    }
	
	
}
