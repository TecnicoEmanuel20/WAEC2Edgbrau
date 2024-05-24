$(document).on("click", "#btnagregar", function(){
    $("#txtnomproduct").val("");
    $("#txtunitprice").val("");
    $("#cbocategory").empty();
    $("#cbosupplier").empty();
    listarCategoriasProveedores(0, 0);
    $("#switchproduct").hide();
    $("#cbdiscontinued").prop("checked", false);
    $("#hddcodprod").val("0");
    $("#modalproduct").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnomproduct").val($(this).attr("data-prodname"));
    $("#txtunitprice").val($(this).attr("data-produnitprice"));
    $("#cbocategory").empty();
    $("#cbosupplier").empty();
    listarCategoriasProveedores($(this).attr("data-prodcategory"),
                        $(this).attr("data-prodsupplier"));
    $("#switchproduct").show();
    if($(this).attr("data-proddiscontinued") === "true"){
        $("#cbdiscontinued").prop("checked", true);
    }else
        $("#cbdiscontinued").prop("checked", false);
    $("#hddcodprod").val($(this).attr("data-prodcod"));
    $("#modalproduct").modal("show");
});
$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/product/register",
        contentType: "application/json",
        data: JSON.stringify({
            productid: $("#hddcodprod").val(),
            productname: $("#txtnomproduct").val(),
            unitprice: $("#txtunitprice").val(),
            categoryid: $("#cbocategory").val(),
            supplierid: $("#cbosupplier").val(),
            discontinued: $("#cbdiscontinued").prop("checked")
        }),
        success: function(resultado){
            if(resultado.resultado)
                listarProductos();
            alert(resultado.mensaje);

        }
    });
    $("#modalproduct").modal("hide");
});

function listarCategoriasProveedores(idcategoria, idproveedor){
    $.ajax({
        type: "GET",
        url: "/category/list",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbocategory").append(
                   `<option value="${value.categoryid}">${value.categoryname}</option>`
                );
            });
            if(idcategoria > 0)
                $("#cbocategory").val(idcategoria);
            $.ajax({
                type: "GET",
                url: "/supplier/list",
                dataType: "json",
                success: function(resultado){
                    $.each(resultado, function(index, value){
                        $("#cbosupplier").append(
                            `<option value="${value.supplierid}">${value.companyname}</option>`
                        );
                    });
                    if(idproveedor > 0)
                        $("#cbosupplier").val(idproveedor);
                }
            });
        }
    });
}

function listarProductos(){
    $.ajax({
        type: "GET",
        url: "/product/list",
        dataType: "json",
        success: function(resultado){
            $("#tblproduct > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproduct > tbody").append(`<tr>`+
                `<td>${value.productid}</td>`+
                `<td>${value.productname}</td>`+
                `<td>${value.unitprice}</td>`+
                `<td>${value.category.categoryname}</td>`+
                `<td>${value.supplier.companyname}</td>`+
                `<td><button type="button" class="btn btn-info btnactualizar" `+
                ` data-prodcod="${value.productid}" `+
                ` data-prodname="${value.productname}" `+
                ` data-produnitprice="${value.unitprice}" `+
                ` data-proddiscontinued="${value.discontinued}" `+
                ` data-prodcategory="${value.category.categoryid}" `+
                ` data-prodsupplier="${value.supplier.supplierid}">Actualizar`+
                `</button></td></tr>`);
            })
        }
    });
}