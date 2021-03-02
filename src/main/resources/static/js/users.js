function AbrirModalRegistroUsuario()
{
	$("#modal_registro_usuario").modal('show');
}

function userRegister()
{
	$("#modal_registro_usuario").modal('hide');
	Swal.fire("Mensaje de confirmación","Datos registrados de forma correcta","success");
}