package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import modelo.Rector;
import modelDAO.*;
import modelo.Colegio;
import modelo.Docente;

/**
 * Servlet implementation class Control_Rector
 */
@WebServlet("/Control_Rector")
public class Control_Rector extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Rector us = new Rector();
    RectorDAO usdao = new RectorDAO();
    Colegio colegio = new Colegio();
    ColegioDao coldao = new ColegioDao();
    Docente doc = new Docente();
    DocenteDAO docdao = new DocenteDAO();
    String vect[];
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control_Rector() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
         PrintWriter out;
         out = response.getWriter();
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu"); 
        if(menu.equalsIgnoreCase("Rector")){
        	 switch (accion){
             case "delete":
            	 String id = request.getParameter("id");
            	 docdao.delete(id);
            	 colegio = coldao.buscar(String.valueOf(us.getColegio()));
                 request.setAttribute("colegio", colegio);
            	 RequestDispatcher rd2 = request.getRequestDispatcher("admin.jsp");
            	 rd2.include(request, response);
            	 break;
            	 default:
            		 
        	 }
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
         PrintWriter out;
         out = response.getWriter();
        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu"); 
        if(menu.equalsIgnoreCase("Rector")){
        	 switch (accion){
             case "Ingresar":
            String user = request.getParameter("txtuser");
        String pass = request.getParameter("txtpass");
        us=usdao.validar(user, pass);
            if(us.getColegio() != null){      
                colegio = coldao.buscar(String.valueOf(us.getColegio()));
                    request.setAttribute("colegio", colegio);
            out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal ('Login Exitoso!','Acceso Concedido','success' );");
            out.println("});");
            out.println("</script>");    
             request.getSession().setAttribute("resultado",us.getColegio());
           HttpSession sesion = request.getSession();
             sesion.setAttribute("user", us);
RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
rd.include(request, response);
           
        }else{
            out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal ('Usuario no Encontrado!','Intente Nuevamente!','error' )");
            out.println("});");
            out.println("</script>");
             HttpSession sesion = request.getSession();
             sesion.invalidate();
            RequestDispatcher rd = request.getRequestDispatcher("admin_login.jsp");
rd.include(request, response);
        }
        break;
        case "Registrar":

    String nombre = request.getParameter("txtnombre");
        String cedula = request.getParameter("txtcedula");
        String codigo = request.getParameter("txtcodigo");
        String email = request.getParameter("txtemail");
        String pas = request.getParameter("txtpassword");
        doc.setNombre(nombre);
        doc.setCedula(cedula);
        doc.setCodigo(codigo);
        doc.setEmail(email);
        doc.setPassword(pas);
        doc.setColegio(Integer.parseInt(request.getSession().getAttribute("resultado").toString()));
                    docdao.agregar(doc);
String asunto = "Credenciales de Acceso al Objeto Virtual de Aprendizaje"; 
                String mensaje = "<b> El Usuario es tu Número de Cedula:</b> "+doc.getCedula()+", <b>o el Código:</b> "+doc.getCodigo()+",<br> <b>Contraseña:</b> "+doc.getPassword()+"<br> <b>Te recomendamos Cambiar la Contraseña que te fue asignada por defecto.</b><br> <b>Accede al Siguiente Link:</b> http://objetovirtualreciclaje.jelastic.saveincloud.net/admin_login.jsp";
                String remitente = "nelsonamayacalderon@gmail.com";  //Para la dirección nomcuenta@gmail.com
                String clave = "eyutdqabiccriqeq";
                Properties props = System.getProperties();
                props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
                props.put("mail.smtp.user", remitente);
                props.put("mail.smtp.clave", clave);    //La clave de la cuenta
                props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
                props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
                props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
String destinatario = doc.getEmail();
                Session session = Session.getDefaultInstance(props);
                MimeMessage message = new MimeMessage(session);

                try {
                    message.setFrom(new InternetAddress(remitente));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));   //Se podrían añadir varios de la misma manera
                    message.setSubject(asunto);
                    message.setText(mensaje,"UTF-8","html");
                    Transport transport = session.getTransport("smtp");
                    transport.connect("smtp.gmail.com", remitente, clave);
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();
                }
                catch (MessagingException me) {
                    me.printStackTrace();   //Si se produce un error
                }
                                 	
                colegio = coldao.buscar(String.valueOf(us.getColegio()));
                request.setAttribute("colegio", colegio);
                request.getSession().setAttribute("resultado",us.getColegio()); 
            RequestDispatcher rd8 = request.getRequestDispatcher("admin.jsp");
rd8.include(request, response);
break;
        case "Salir":
            
            	HttpSession sesion = request.getSession();
                sesion.invalidate();
                    RequestDispatcher rd3 = request.getRequestDispatcher("admin_login.jsp");
rd3.include(request, response);
        break;
           default:
        	   sesion = request.getSession();
             sesion.invalidate();

            RequestDispatcher rd4 = request.getRequestDispatcher("admin_login.jsp");
rd4.include(request, response);
}
            
        }else{
        	RequestDispatcher rd5 = request.getRequestDispatcher("admin_login.jsp");
        	rd5.include(request, response);
        }
        
    
    
	}

}
