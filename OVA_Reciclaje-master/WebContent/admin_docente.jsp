<%-- 
    Document   : admin_docente
    Created on : 5/06/2020, 08:38:18 PM
    Author     : NELSON
--%>
<%@page import="Config.Conexion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="modelDAO.*"%>
<%@page import="java.util.Iterator"%>
<%@page import="modelo.Nivel"%>
<%@page import="java.util.List"%>
<%@page import="modelDAO.NivelDAO"%>
<%@page import="modelDAO.AlumnoDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="modelDAO.DocenteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
        <link rel="shortcut icon"  href="${colegio.getImagen()}">

	<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="javaScript/alertifyjs/css/themes/default.css">
<link rel="stylesheet" type="text/css" href="javaScript/alertifyjs/css/alertify.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
  	<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">-->
   <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css"></script>
    <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script> 
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js">   
   <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="css/admin_docente_styles.css">
<script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
    

        <title>Panel Docente</title>
    </head>
    <body>
          <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        
                         <img class="logo" src="${colegio.getImagen()}" width="150px" height="80px"  alt="" title="${colegio.getNombre()}">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
                        
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="nav nav-pills col-10" id="pills-tab" role="tablist">
  <li class="nav-item">
    <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab" aria-controls="pills-home" aria-selected="true">Estudiantes Certificados</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab" aria-controls="pills-profile" aria-selected="false">Añadir Preguntas</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-cuestionario-tab" data-toggle="pill" href="#pills-cuestionario" role="tab" aria-controls="pills-cuestionario" aria-selected="false">Cuestionarios</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" id="pills-config-tab" data-toggle="pill" href="#pills-config" role="tab" aria-controls="pills-config" aria-selected="false">Configurar Cuenta</a>
  </li>
</ul>
      
     <ul class="navbar-nav mr-auto">
  <form  action="Control_Docente" method="POST">
    
              <button name="accion" value="Salir" class="btn btn-danger">Cerrar Sesión</button>
         
 </form>
    </ul>
    
  </div>
</nav>
  <div class="container">
  <div class="row">
      <div class="col-md-12">
         <div class="tab-content" id="pills-tabContent">
             <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                 <h1 class="text-center text-primary">Listado de Alumnos Certificados</h1>
                 <br>
                   <div class="col-sm-12" style=" margin-top: -10px;">

                    <%                                       
                        AlumnoDAO cad = new AlumnoDAO();
                   ResultSet rs;
                   String colegio= request.getSession().getAttribute("resultado").toString();  
                    rs=cad.listarTodos(colegio);                   
                    %>  
            <table id="minhatabela" class="display responsive table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th class="text-center">N° CERTIFICADO</th>
                        <th class="text-center">NOMBRE</th>
                        <th class="text-center">DOCUMENTO</th>
                        <th class="text-center">NIVEL ACADEMICO</th>
                        <th class="text-center">FECHA</th>

                    </tr>
                </thead>
                <tbody>     
                    <%while(rs.next()){%>
                        <tr>                            
                            <td class="text-center"><h5  id="intro"><%=rs.getString("cd.numero_cert")%></h5></td>
                            <td class="text-center"><h5  id="intro"><%=rs.getString("al.nombre")%></h5></td>
                            <td class="text-center"><h5  id="intro"><%=rs.getString("cd.id_alumno")%></h5></td>                     
                            <td class="text-center"><h5  id="intro"><%=cad.buscarNivel(String.valueOf(rs.getString("cd.id_nivel")))%></h5></td>
                            <td class="text-center"><h5  id="intro"><%=rs.getString("cd.fecha")%></h5></td>
                           
                        </tr>
                       <%}%>                       
                </tbody>
            </table> 
               <script src="js/tablajs.js" type="text/javascript"></script>
        </div>  
             </div>
                
  <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
      <form action="Control_Docente" method="POST" id="miForm"> 
      <div class="container col-12">
          <div class="row">
              <div class="col-12">
                  <h1 class="text-center text-primary">Añadir Preguntas</h1>
              </div>
              <div class="col-4">
                  <div class="form-group">
    <label for="Usuario">Nivel Academico</label>
    <div class="input-group mb-3">
    <select name="txtNivel" class="select form-control" id="cat">
                            <%
                    NivelDAO daos = new NivelDAO();
                    List<Nivel>lista=daos.listar();
                    Iterator<Nivel>itera=lista.iterator();
                    Nivel em = null;
                    while(itera.hasNext()){
                        em=itera.next();                    
                    %>                        
                        <option value="<%=em.getId()%>"><%=em.getDescripcion()%></option>
                                <%}%>
                            </select>
  </div>
          </div>
              </div>
              <div class="col-12">
                   <div class="form-group">
    <label for="Usuario">Titulo Pregunta</label>
    <div class="input-group mb-3">
        <textarea class="form-control" aria-label="With textarea" name="txtpregunta" id="pregunta" required="" style="border-radius: 15px"></textarea>
  </div>
          </div>
              </div>
                     
      <div class="col-md-6">
          <div class="form-group">
    <label for="Usuario">Opción 1:</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>A</strong></span>
    </div>
    <input type="text" class="form-control" id="nombre" name="txtopc1" required="">
  </div>
          </div>
          <div class="form-group">
    <label for="Usuario">Opción 2:</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>B</strong></span>
    </div>
    <input type="text" class="form-control" id="cedula" name="txtopc2" required="">
  </div>
          </div>
          <div class="form-group">
    <label for="Usuario">Opción 3:</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>C</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" name="txtopc3" required="">
  </div>
          </div>
          <div class="form-group">
    <label for="Usuario">Opción 4:</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>D</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" name="txtopc4" required="">
  </div>
          </div>
      </div>
      <div class="col-md-6">
          <div class="form-group col-6">
    <label for="Usuario">Puntuación</label>
    <div class="input-group mb-3">
        <input type="number" class="form-control" id="punt1" name="punt1" value="0" min="0" max="10">
  </div>
          </div>
          <div class="form-group col-6">
    <label for="Usuario">Puntuación</label>
    <div class="input-group mb-2">
 <input type="number" class="form-control" id="punt2" name="punt2" value="0" min="0" max="10">
    </div>
          </div>
          <div class="form-group col-6">
    <label for="Usuario">Puntuación</label>
    <div class="input-group mb-3">
 <input type="number" class="form-control" id="punt3" name="punt3" value="0" min="0" max="10">
    </div>
          </div>
          <div class="form-group col-6">
    <label for="Usuario">Puntuación</label>
    <div class="input-group mb-3">
 <input type="number" class="form-control" id="punt4" name="punt4" value="0" min="0" max="10">
    </div>
          </div>
          
      </div>
              <br>
              <br>
              <div class="col-12">
                   <div class="row justify-content-center">
                       <div class="col-6">
                  <div class="form-group">
               <input class=" btn btn-block btn-success" type="submit" value="Agregar" id="agregar" name="accion"/>
               <a class=" btn btn-block btn-warning" id="clearbutton" href="#">Cancelar</a>
               
                  </div>
                  </div>
                  </div>
              </div>

              
              </div>
      </div>
                            </form>
      </div>
         <div class="tab-pane fade" id="pills-config" role="tabpanel" aria-labelledby="pills-config-tab">
      <form action="Control_Docente" id="miForm2" method="POST" name="mi_formulario" onSubmit="return validar_clave()"> 
      <div class="container col-12">
          <div class="row justify-content-center">
          <div class="border border-primary rounded col-4 m-3">
          <div class="row justify-content-center">
          <h3 class="text-primary">Actualizar Contraseña</h3>
               <div class="col-md-12">
          <div class="form-group">
    <label for="Usuario">Contraseña Actual</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><i class="fa fa-unlock-alt" aria-hidden="true"></i></span>
    </div>
    <input type="password" class="form-control" id="password" name="pass">
  </div>
          </div>
          <div class="form-group">
    <label for="Usuario">Nueva Contraseña</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><i class="fa fa-lock" aria-hidden="true"></i></span>
    </div>
    <input type="password" class="form-control" id="password2" name="pass2">
  </div>
          </div>
          <div class="form-group">
    <label for="Usuario">Confirmar Contraseña</label>
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><i class="fa fa-lock" aria-hidden="true"></i></span>
    </div>
    <input type="password" class="form-control" id="password3" name="pass3">
  </div>
          </div>
           <div class="col-12">
                   <div class="row justify-content-center">
                       <div class="col-6">
                  <div class="form-group">
               <input class=" btn btn-block btn-success" type="submit" value="Confirmar" id="confirmar" name="accion"/>
               <a class=" btn btn-block btn-warning" id="clearbutton2" href="#">Cancelar</a>
               
                  </div>
                  </div>
                  </div>
              </div>
      </div>
          </div>
          </div>
          </div> 
          </div>
          </form>
          </div>  
          
            
             <div class="tab-pane fade" id="pills-cuestionario" role="tabpanel" aria-labelledby="pills-cuestionario-tab">
      <div class="container col-12">
          <div class="row">

          <div class="border border-primary rounded col-6">
          <div class="row justify-content-center col-12" >
          <h4 class="text-warning">Cuestionario Primaria</h4>
          <%
                        PreguntaDAO cad2 = new PreguntaDAO();
                    ResultSet rs2;
                     int cont=1;
                    rs2=cad2.listarTodosDocente();                  
                    %> 
                    <%while(rs2.next()){%>                   
                        <div class="col-12">
                   <div class="form-group">
    <label for="Usuario"><%=cont%>. <%=rs2.getString("descripcion")%></label>

          </div>
              </div>

                  <div class="col-md-12">
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>A</strong></span>
    </div>
    <input type="text" class="form-control" value="<%=rs2.getString("opc")%>" id="nombre" name="txtopc1" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>B</strong></span>
    </div>
    <input type="text" class="form-control" id="cedula" value="<%=rs2.getString("opc2")%>" name="txtopc2" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>C</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" value="<%=rs2.getString("opc3")%>" name="txtopc3" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>D</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" value="<%=rs2.getString("opc4")%>" name="txtopc4" readonly="">
  </div>
          </div>
      </div>   
   <%cont++;}%>
          </div>
          </div>
        <div class="border border-primary rounded col-6">
        <div class="row justify-content-center col-12" >
          <h4 class="text-warning">Cuestionario Secundaria</h4>
          <%
                        PreguntaDAO cad3 = new PreguntaDAO();
                    ResultSet rs3;
                     int cont2=1;
                    rs3=cad3.listarTodosSecundariaDocente();                  
                    %> 
                    <%while(rs3.next()){%>                   
                        <div class="col-12">
                   <div class="form-group">
    <label for="Usuario"><%=cont2%>. <%=rs3.getString("descripcion")%></label>

          </div>
              </div>

                  <div class="col-md-12">
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>A</strong></span>
    </div>
    <input type="text" class="form-control" value="<%=rs3.getString("opc")%>" id="nombre" name="txtopc1" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1"><strong>B</strong></span>
    </div>
    <input type="text" class="form-control" id="cedula" value="<%=rs3.getString("opc2")%>" name="txtopc2" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>C</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" value="<%=rs3.getString("opc3")%>" name="txtopc3" readonly="">
  </div>
          </div>
          <div class="form-group">
    <div class="input-group mb-3">
    <div class="input-group-prepend">
    <span class="input-group-text" id="basic-addon1"><strong>D</strong></span>
    </div>
    <input type="text" class="form-control" id="codigo" value="<%=rs3.getString("opc4")%>" name="txtopc4" readonly="">
  </div>
          </div>
      </div>   
   <%cont2++;}%>
          </div>
          </div>
          </div> 
          </div>
          
          </div>   
  </div>
</div>
      </div> 
  </div>
  <script src="js/admin_docentejs.js" type="text/javascript"></script>
    </body>
</html>
