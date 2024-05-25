$(document).on("click", ".btnnuevo", function () {
    $("#modalpublicacion").modal("show");
    $("#txtidpublicacion").val("0");
    $("#txttitulo").val("");
    $("#txtresumen").val("");
    $("#txtfechapublicacion").val("dd-MM-yyyy");
    $("#cboAutor").empty();
});

