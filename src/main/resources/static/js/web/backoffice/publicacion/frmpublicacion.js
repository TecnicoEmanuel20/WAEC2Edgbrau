$(document).on("click", ".btnnuevo", function () {
    $("#modalpublicacion").modal("show");
    $("#txtidpublicacion").val("0");
    $("#txttitulo").val("");
    $("#txtresumen").val("");
    $("#txtfechapublicacion").val("dd-MM-yyyy");
    $("#cboAutor").empty();
    cagarAutor(0);
});

$(document).on("click",".btnguardar",function (){

    $.ajax({
       type: "POST",
       url: "/publicacion/registrar",
       contentType: "application/json",
       data: JSON.stringify({
           idpublicacion: $("#txtidpublicacion").val(),
           titulo: $("#txttitulo").val(),
           resumen: $("#txtresumen").val(),
           fechpublicacion: $("#txtfechapublicacion").val(),
           idautor: $("#cboAutor").val()
       }),
        success: function (resultado) {
            if(resultado.resultado) {
                alert(resultado.mensaje)
                $("#modalpublicacion").modal("hide");
                listadoPublicacion();
            }else{
                alert(resultado.mensaje)
            }
        }
    });

});

$(document).on("click",".btnactualizar", function () {

    $.ajax({
        type: "GET",
        url: "/publicacion/" + $(this).attr("data-idpublicacion"),
        dataType: "json",
        success: function (resultado) {
            $("#txtidpublicacion").val(resultado.idpublicacion);
            $("#txttitulo").val(resultado.titulo);
            $("#txtresumen").val(resultado.resumen);
            $("#txtfechapublicacion").val(resultado.fechpublicacion);
            cagarAutor(resultado.idautor);
            $("#cboAutor").val(resultado.idautor);
            $("#modalpublicacion").modal("show");
        }
    });

});

function listadoPublicacion() {
    $.ajax({
       type: "GET",
       url: "/publicacion/list",
       dataType: "json",
       success: function (resultado) {
           $("#tbpublicacion > tbody").html("");
           $.each(resultado, function (index, value) {
                $("#tbpublicacion > tbody").append(
                    `<tr>
                        <td>${value.idpublicacion}</td>
                        <td>${value.titulo}</td>
                        <td>${value.resumen}</td>
                        <td>${value.fechpublicacion}</td>
                        <td>${value.autor.nomautor}</td>
                        <td>
                            <button class="btn btn-info btnactualizar"
                                    data-idpublicacion="${value.idpublicacion}">Actualizar</button>
                        </td>
                    </tr>`
                )
           });
       }
    });
}

function cagarAutor(idautor) {
    $.ajax({
        type: "GET",
        url: "/publicacion/autor",
        dataType: "json",
        success: function (resultado) {
            $.each(resultado, function (index, value) {
                $("#cboAutor").append(
                    `<option value="${value.idautor}">${value.nomautor}</option>`
                )
            });
            if(idautor > 0) {
                $("#cboAutor").val(idautor);
            }
        }
    });
}