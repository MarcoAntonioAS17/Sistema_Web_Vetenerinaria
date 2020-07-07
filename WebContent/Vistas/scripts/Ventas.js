
var contador =true;
var Total;

$(document).ready(function(){
	
	contador=true;
	
	$("#Cantidad").val("1");
	cargar_productos();
	$("#otro_opc").val(1);
	ocultar_elementos();
	
	$("#Efectivo").keyup(function(e){
		$("#Cambio").val(" "+($(this).val()-Total));
	});
	
	$("#otro_opc").change(function() {
		$("#cont-producto").children().toggle(500);
		$("#list_prod").hide();
		
	});
	
	$('#guardar').click(function(e){
		if($("#Cambio").val()<0){
			alert("Dinero insuficiente");
			return;
		}
		var confirma = confirm("¿Estás seguro que deseas finalizar la venta?");
		
		if(confirma == true){
			limpiar_campos();
			alert("Venta guardada");
			location.reload();
			
		}else{
			return;
		}
    });
    
    $('#cancelar').click(function(e){
		var confirma = confirm("¿Estás seguro que deseas CANCELAR la Venta?");
		
		if(confirma == true){
			limpiar_campos();
		$.post("../../Ventas",{
	 		accion : "cancelar_venta"
		},function(responseText){
			if(responseText != "false"){
				alert("La venta ha sido cancelada");
				location.reload();
			}else{
				  alert("Error al cancelar venta");
				  return;
			}
		});
			
		}else{
			return;
		}
	});
	
	$('#agregar').click(function(e){
	 	
		var falso=false;
		var varFecha = $("#Fec_C").val();
	   	var varHora = $("#Hra_C").val();
	   
	   	var varCliente = $("#Clv_Client").val();
	   	var varProducto = $("#Clv_Prod").val();
	   	var varCantidad = $("#Cantidad").val();
	   	
	   	$("#Cantidad").css({"border-color":"black","color":"black"});
		$("#Clv_Prod").css({"border-color":"black","color":"black"});
	   	
		if(varCantidad == "" || varCantidad <= 0){
	   		$("#Cantidad").css({"border-color":"red","color":"red"});
	   		falso=true;
	   	}
	   	if(varProducto == ""){
	   		$("#Clv_Prod").css({"border-color":"red","color":"red"});
	   		falso="true";
	   	}
	   	if(falso){
	   		alert("Faltan registros por acompletar");
	   		return;
	   	}
	   	
	 	if(contador){
	 		$.post("../../Ventas",{
		 		accion : "agregar_producto",
				Fecha : varFecha,
				Hora : varHora,
				Producto: varProducto,
				Cantidad: varCantidad,
				Cliente: varCliente
			},function(responseText){
				
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					$('#tabla').show(500);
					limpiar_campos();
				}else{
					  alert("Error al agregar producto");
					  return;
				}
			});
	 		contador=false;
	 	}else{
	 		$.post("../../Ventas",{
		 		accion : "agregar_producto2",
				Producto: varProducto,
				Cantidad: varCantidad
			},function(responseText){
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					limpiar_campos();
				}else{
					  alert("Error al agregar producto");
					  return;
				}
			});
	 	}
	   	
	});
	
	$("#Otro_Agregar").click(function(){
		
    	var falso=false;
		var varFecha = $("#Fec_C").val();
	   	var varHora = $("#Hra_C").val();
	   	var varCliente = $("#Clv_Client").val();
	   
	   	var varServicio = $("#servicio").val(); 
	   	var varPrecio = $("#Otro_Precio").val();
	   	
	   	$("#Otro_Precio").css({"border-color":"black","color":"black"});
	   	
		if(varPrecio == "" ){
	   		$("#Otro_Precio").css({"border-color":"red","color":"red"});
	   		falso=true;
	   	}
	   	if(falso){
	   		alert("Faltan registros por acompletar");
	   		return;
	   	}
	   	
	 	if(contador){
	 		$.post("../../Ventas",{
		 		accion : "agregar_servicio",
				Fecha : varFecha,
				Hora : varHora,
				Cliente: varCliente,
				Precio: varPrecio,
				Servicio: varServicio
			},function(responseText){
				
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					$('#tabla').show(500);
					limpiar_campos();
				}else{
					  alert("Error al agregar servicio");
					  return;
				}
			});
	 		contador=false;
	 	}else{
	 		$.post("../../Ventas",{
		 		accion : "agregar_servicio2",
				Precio: varPrecio,
				Servicio: varServicio
			},function(responseText){
				if(responseText != "false"){
					Total=mostrar_registros(responseText);
					limpiar_campos();
				}else{
					  alert("Error al agregar servicio");
					  return;
				}
			});
	 	}
	   	
    });
	
});

function mostrar_registros(responseJson){
	$("tbody").empty();
	var suma=0;
	var datos = JSON.parse(responseJson);
	var i=0;
	for(i in datos){

		$("tbody").append("<tr>" +
				"<td>"+datos[i].Codigo+"</td>" +
				"<td>"+datos[i].Nombre+"</td>" +
				"<td>"+datos[i].Cantidad+"</td>" +
				"<td> $"+datos[i].Precio+"</td>" +
				"<td> $"+(datos[i].Precio*datos[i].Cantidad).toFixed(2)+"</td>" +
				"<td>"+
				"<button onclick=\"eliminar("+datos[i].Codigo+","+datos[i].Cantidad+")\" type=\"button\" class=\"eliminar_pro\"> <img width=\"25px\"  alt=\"icono-eliminar\" src=\"../img/eliminar-icono.svg\"></button>"+
				"</td>"+
                "</tr>");
		
		suma+=(datos[i].Precio*datos[i].Cantidad);
	}
	$("tbody").append("<tr>" +
			"<td></td><td></td>" +
			"<td></td>" +
			"<td> Total </td>" +
			"<td> $"+suma.toFixed(2)+"</td>" +
			"<td></td>"+
            "</tr>");
	return suma;
}

function cargar_productos(){
	$("datalist").empty();
	$.post("../../Ventas",{
		accion : "cargar_productos"
	},function(responseJson){
		var datos = JSON.parse(responseJson);

		for(var i in datos){
			$("datalist").append(
				"<option value="+datos[i].Codigo+">"+datos[i].Codigo+", "+datos[i].Nombre+"</option>"
				);
		}
	}
	);
}

function eliminar(varProducto, varCantidad){

	$.post("../../Ventas",{
 		accion : "borrar_producto",
		Producto: varProducto,
		Cantidad: varCantidad
	},function(responseText){
		if(responseText != "false"){
			Total=mostrar_registros(responseText);
		}else{
			  alert("Error al agregar producto");
			  return;
		}
	});
}

function limpiar_campos(){
	$("#Clv_Prod").val("");	
	$("#Cantidad").val(1);
	$("#Efectivo").val("");
	$("#Cambio").val("-1");
	 $("#Otro_Precio").val("");
}

function ocultar_elementos(){

	$('#tabla').hide(500);

}


