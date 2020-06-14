document.getElementById("clearbutton").onclick = limpiaCampo;

function limpiaCampo() {
	document.getElementById("miForm").reset();
}

document.getElementById("clearbutton2").onclick = limpiaCampo2;

function limpiaCampo2() {
	document.getElementById("miForm2").reset();
}

function comprobarClave(){
    clave1 = document.miForm2.pass2.value;
    clave2 = document.miForm2.pass3.value;

    if (clave1 == clave2){
       alert("Las dos claves son iguales...\nRealizaríamos las acciones del caso positivo");
    }else{
       alert("Las dos claves son distintas...\nRealizaríamos las acciones del caso negativo");
    }    
}

function validar_clave() {
	var caract_invalido = "";
	var caract_longitud = 6;
	var cla1 = document.mi_formulario.pass.value;
	var cla2 = document.mi_formulario.pass2.value;
	var cla3 = document.mi_formulario.pass3.value;
	if ( cla1 == '') {
		document.mi_formulario.pass.focus();
		 alertify.alert("Advertencia!","Debes Ingresar tu Contraseña Actual");
		return false;
		}
	else if( cla2 == '') {
		document.mi_formulario.pass2.focus();
		alertify.alert("Advertencia!","Debes Ingresar la Nueva Contraseña");
		return false;
		}
	else if( cla3 == '') {
		document.mi_formulario.pass3.focus();
		alertify.alert("Advertencia!","Debes Ingresar Confirmar la Nueva Contraseña");
	return false;
	}
	else {
	if (cla2 != cla3) {
		document.mi_formulario.pass2.value = "";
		document.mi_formulario.pass3.value = "";
		document.mi_formulario.pass2.focus();
		alertify.alert("Advertencia!","Las Constraseñas Introducidas no Son Iguales");
	return false;
	}
	else {
	return true;
	      }
	   }
	}