$(document).on("click", "#btnagregar", function(){
    $("#txtnombre").val("");
    $("#txtapellido").val("");
    $("#txtemail").val("");
    $("#txtemail").prop('readonly', false);
    $("#txtusuario").val("");
    $("#txtusuario").prop('readonly', false);
    $("#txtpassword").val("");
    $("#txtpassword").prop('readonly', false);
    $("#btnenviar").hide();

});

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/seguridad/usuario",
        contentType: "application/json",
        data: JSON.stringify({
            idusuario: $("#hddidusuario").val(),
            nombres: $("#txtnombre").val(),
            apellidos: $("#txtapellido").val(),
            email: $("#txtemail").val(),
            email: $("#txtpassword").val(),
            activo: $("#cbactivo").prop("checked"),
            nomusuario: $("#txtnomusuario").val()
        }),
        success: function(resultado){
            if(resultado.resultado)
            alert(resultado.mensaje);

        }
    });
});
