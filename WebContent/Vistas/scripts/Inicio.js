
$(document).ready(function(){
	
	//Cargar datos numericos
	$.post("../../Principal",{
		accion : "C_Datos"
	},function(responseText){
		var datos = JSON.parse(responseText);
		
		$("#Prod_Tot").text(datos.Totales);
		$("#Agotarse").text(datos.Agotarse);
		$("#caducar").text(datos.Caducar);
	});

	
	cargar_citas(6);
	
	$("#hoy").click(function(e){
		$(".selected").removeClass('selected');
		
		$("#citas-dia").show(300);
		$("#citas-semana").hide(300);
		$("#citas-mes").hide(300);
		$(this).next().addClass('selected');
	});
	
	$("#semana").click(function(e){
		$(".selected").removeClass('selected');
		
		$("#citas-dia").hide(300);
		$("#citas-semana").show(300);
		$("#citas-mes").hide(300);
		$(this).next().addClass('selected');
	});
	
	$("#mes").click(function(e){
		$(".selected").removeClass('selected');
		
		$("#citas-dia").hide(300);
		$("#citas-semana").hide(300);
		$("#citas-mes").show(300);
		$(this).next().addClass('selected');
	});
	
	$("#select-tipo").change(function(e){
		cargar_citas($(this).val());
	});
	
	
});

function cargar_citas(opc){

	//Dia
	$.post("../../Principal",{
		accion : "1",
		Opcion: opc
	},function(responseText){
		var datos = JSON.parse(responseText);
		var elemento = $("#citas-dia"); 
		elemento.empty();
		
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		var cent = false;
		var i=0;
		
		for( i in datos){
			cent = true;
			 date = new Date(datos[i].Fecha); 
			 date.setDate(date.getDate() + 1);
			 
			 elemento.append("<div id=\"D"+datos[i].IDCita+"\"></div>");
			 
			 var hijo = $("#D"+datos[i].IDCita);
			 
			 if(datos[i].Tipo.includes("Este")){
				 hijo.append(
						 "<img src=\"../img/estetica-icono.svg\" alt=\"icono-estetica\">");
			 }else{
				 if(datos[i].Tipo.includes("Consu")){
					 hijo.append(
							 "<img src=\"../img/consulta-icono.svg\" alt=\"icono-consulta\">");
				 }else{
					 if(datos[i].Tipo.includes("Vacun")){
						 hijo.append(
								 "<img src=\"../img/vacuna-icono.svg\" alt=\"icono-vacuna\">");
					 }else{
						 if(datos[i].Tipo.includes("Operac")){
							 hijo.append(
									 "<img src=\"../img/operacion-icono.svg\" alt=\"icono-operacion\">");
						 }else{
						 	 hijo.append(
								 "<img src=\"../img/otros-icono.svg\" alt=\"icono-otros\">");
							 
						 }
					 }
				 }
			 }
			 if(datos[i].Hora != 'null'){
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +". Hora: "+ datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }else{
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }
			 
		}
		if(!cent){
			elemento.append(
				"<div>"+
					"<div>"+
                	"<p>No hay citas registradas</p>"+
					"</div>"+
				"</div>"
			);
		}else{
			i++;
		}
		$("#hoy").next().text("Hoy ("+(i)+")");
	});
	
	///Semana
	$.post("../../Principal",{
		accion : "2",
		Opcion: opc
	},function(responseText){
		var datos = JSON.parse(responseText);
		var elemento = $("#citas-semana"); 
		elemento.empty();
		
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		var cent = false;
		var i=0;
		
		for( i in datos){
			cent = true;
			 date = new Date(datos[i].Fecha); 
			 date.setDate(date.getDate() + 1);
			 
			 elemento.append("<div id=\"S"+datos[i].IDCita+"\"></div>");
			 
			 var hijo = $("#S"+datos[i].IDCita);
			 
			 if(datos[i].Tipo.includes("Este")){
				 hijo.append(
						 "<img src=\"../img/estetica-icono.svg\" alt=\"icono-estetica\">");
			 }else{
				 if(datos[i].Tipo.includes("Consu")){
					 hijo.append(
							 "<img src=\"../img/consulta-icono.svg\" alt=\"icono-consulta\">");
				 }else{
					 if(datos[i].Tipo.includes("Vacun")){
						 hijo.append(
								 "<img src=\"../img/vacuna-icono.svg\" alt=\"icono-vacuna\">");
					 }else{
						 if(datos[i].Tipo.includes("Operac")){
							 hijo.append(
									 "<img src=\"../img/operacion-icono.svg\" alt=\"icono-operacion\">");
						 }else{
						 	 hijo.append(
								 "<img src=\"../img/otros-icono.svg\" alt=\"icono-otros\">");
							 
						 }
					 }
				 }
			 }
			 if(datos[i].Hora != 'null'){
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +". Hora: "+ datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }else{
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }
			 
		}
		if(!cent){
			elemento.append(
				"<div>"+
					"<div>"+
                	"<p>No hay citas registradas</p>"+
					"</div>"+
				"</div>"
			);
		}else{
			i++;
		}
		$("#semana").next().text("Semana ("+(i)+")");
	});
	
	//MEs
	$.post("../../Principal",{
		accion : "3",
		Opcion: opc
	},function(responseText){
		var datos = JSON.parse(responseText);
		var elemento = $("#citas-mes"); 
		elemento.empty();
		
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		var cent = false;
		var i=0;
		
		for( i in datos){
			cent = true;
			 date = new Date(datos[i].Fecha); 
			 date.setDate(date.getDate() + 1);
			 
			 elemento.append("<div id=\"M"+datos[i].IDCita+"\"></div>");
			 
			 var hijo = $("#M"+datos[i].IDCita);
			 
			 if(datos[i].Tipo.includes("Este")){
				 hijo.append(
						 "<img src=\"../img/estetica-icono.svg\" alt=\"icono-estetica\">");
			 }else{
				 if(datos[i].Tipo.includes("Consu")){
					 hijo.append(
							 "<img src=\"../img/consulta-icono.svg\" alt=\"icono-consulta\">");
				 }else{
					 if(datos[i].Tipo.includes("Vacun")){
						 hijo.append(
								 "<img src=\"../img/vacuna-icono.svg\" alt=\"icono-vacuna\">");
					 }else{
						 if(datos[i].Tipo.includes("Operac")){
							 hijo.append(
									 "<img src=\"../img/operacion-icono.svg\" alt=\"icono-operacion\">");
						 }else{
						 	 hijo.append(
								 "<img src=\"../img/otros-icono.svg\" alt=\"icono-otros\">");
							 
						 }
					 }
				 }
			 }
			 if(datos[i].Hora != 'null'){
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +". Hora: "+ datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }else{
				 hijo.append(
		                    "<div>"+
		                        "<p id='fecha'>"+date.toLocaleDateString("es-ES", options) +"</p>"+
		                        "<p>"+datos[i].NombreC+"</p>"+
		                        "<p>"+datos[i].NombreM+"</p>"+
		                    "</div>"
		                );
			 }
			 
		}
		if(!cent){

			elemento.append(
				"<div>"+
					"<div>"+
                	"<p>No hay citas registradas</p>"+
					"</div>"+
				"</div>"
			);
		}else{
			i++;
		}
		$("#mes").next().text("Mes ("+(i)+")");
	});
	
}
