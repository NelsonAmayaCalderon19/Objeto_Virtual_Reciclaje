function validar_campos() {
	var cla1 = document.mi_formulario.txtuser.value;
	var cla2 = document.mi_formulario.txtpass.value;
	if ( cla1 == '') {
		document.mi_formulario.txtuser.focus();
		 alertify.alert("Advertencia!","Debes Ingresar tu Usuario");
		return false;
		}
	else if( cla2 == '') {
		document.mi_formulario.txtpass.focus();
		alertify.alert("Advertencia!","Debes Ingresar tu Contraseña");
		return false;
		}	
}

function validar() {
	var cla1 = document.mi_form.txtcedula.value;
	var cla2 = document.mi_form.txtpass.value;
	if ( cla1 == '') {
		document.mi_form.txtcedula.focus();
		 alertify.alert("Advertencia!","Debes Ingresar tu N° de Cedula o Código");
		return false;
		}
	else if( cla2 == '') {
		document.mi_form.txtpass.focus();
		alertify.alert("Advertencia!","Debes Ingresar tu Contraseña");
		return false;
		}	
}