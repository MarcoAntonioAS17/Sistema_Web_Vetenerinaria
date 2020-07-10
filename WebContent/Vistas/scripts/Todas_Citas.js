	//Guardar datos en BD
   $(document).ready(function(){
		   
		   mostrar_registros();
		   
		   $.post("../../Grafica",{
				accion : "grafica_citas",
				
				},function(response){
					var resJSON = JSON.parse(response);
					
					cargar_grafica(resJSON);
			});
		   
	       
   });
	   
function mostrar_registros(){
	$("tbody").empty();
	$.post("../../SCitas",{
		accion : "mostrar_todas"
	},function(responseJson){
		
		var datos = JSON.parse(responseJson);
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		
		for(var i in datos){
			date = new Date(datos[i].Fecha); 
			date.setDate(date.getDate() + 1);
			if(datos[i].Hora == "null"){
				 $("tbody").append("<tr>" +
							"<td>"+datos[i].IDCita+"</td>" +
							"<td>"+datos[i].Tipo+"</td>" +
							"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
							"<td>"+datos[i].Hora+"</td>" +
							"<td> "+datos[i].NombreC+"</td>" +
							"<td>"+datos[i].NombreM+"</td>" +
							"<td>"+datos[i].Notas+"</td>" +
							"</tr>");
			 }else{
				 $("tbody").append("<tr>" +
							"<td>"+datos[i].IDCita+"</td>" +
							"<td>"+datos[i].Tipo+"</td>" +
							"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
							"<td>"+datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</td>" +
							"<td> "+datos[i].NombreC+"</td>" +
							"<td>"+datos[i].NombreM+"</td>" +
							"<td>"+datos[i].Notas+"</td>" +
							"</tr>");
			 }
		}		
	}
	);
}

function cargar_grafica(datos){
	//Grafica
	
	let miCanvas = document.getElementById("MiGrafica").getContext("2d");
	var Meses = ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"];
	new Chart(miCanvas,{
		type: "line",
		data:{
			
				
			labels:[Meses[datos.Meses[11]-1],Meses[datos.Meses[10]-1],Meses[datos.Meses[9]-1],Meses[datos.Meses[8]-1],Meses[datos.Meses[7]-1],Meses[datos.Meses[6]-1],
					Meses[datos.Meses[5]-1],Meses[datos.Meses[4]-1],Meses[datos.Meses[3]-1],Meses[datos.Meses[2]-1],Meses[datos.Meses[1]-1],Meses[datos.Meses[0]-1]],
			datasets:[
				{
					lineTension: 0,    
					label: "Citas en general",
					fill: false,
					borderColor: "#70d67f",
					data:[datos.CitasM[11],datos.CitasM[10],datos.CitasM[9],datos.CitasM[8],datos.CitasM[7],datos.CitasM[6],
						datos.CitasM[5],datos.CitasM[4],datos.CitasM[3],datos.CitasM[2],datos.CitasM[1],datos.CitasM[0]]
				},
				{
					lineTension: 0,    
					label: "Citas Estetica",
					fill: false,
					borderColor: "#70B3D6",
					data:[datos.C_Est[11],datos.C_Est[10],datos.C_Est[9],datos.C_Est[8],datos.C_Est[7],datos.C_Est[6],
						datos.C_Est[5],datos.C_Est[4],datos.C_Est[3],datos.C_Est[2],datos.C_Est[1],datos.C_Est[0]]
				},
				{
					lineTension: 0,    
					label: "Citas Consulta",
					fill: false,
					borderColor: "#D6D170",
					data:[datos.C_Vac[11],datos.C_Vac[10],datos.C_Vac[9],datos.C_Vac[8],datos.C_Vac[7],datos.C_Vac[6],
						datos.C_Vac[5],datos.C_Vac[4],datos.C_Vac[3],datos.C_Vac[2],datos.C_Vac[1],datos.C_Vac[0]]
				},
				{
					lineTension: 0,    
					label: "Citas Vacunacion",
					fill: false,
					borderColor: "#D6708A",
					data:[datos.C_Vac[11],datos.C_Vac[10],datos.C_Vac[9],datos.C_Vac[8],datos.C_Vac[7],datos.C_Vac[6],
						datos.C_Vac[5],datos.C_Vac[4],datos.C_Vac[3],datos.C_Vac[2],datos.C_Vac[1],datos.C_Vac[0]]
				},
				{
					lineTension: 0,    
					label: "Citas Operacion",
					fill: false,
					borderColor: "#A870D6",
					data:[datos.C_Ope[11],datos.C_Ope[10],datos.C_Ope[9],datos.C_Ope[8],datos.C_Ope[7],datos.C_Ope[6],
						datos.C_Ope[5],datos.C_Ope[4],datos.C_Ope[3],datos.C_Ope[2],datos.C_Ope[1],datos.C_Ope[0]]
				},
				{
					lineTension: 0,    
					label: "Citas Otros",
					fill: false,
					borderColor: "#6B3864",
					data:[datos.C_Otr[11],datos.C_Otr[10],datos.C_Otr[9],datos.C_Otr[8],datos.C_Otr[7],datos.C_Otr[6],
						datos.C_Otr[5],datos.C_Otr[4],datos.C_Otr[3],datos.C_Otr[2],datos.C_Otr[1],datos.C_Otr[0]]
				}
			]
		}, 
		options: {
			responsive: true,
			title: {
				fontSize: 22,
				fontColor: '#70d67f',
				display: true,
				text: 'Resumen de Citas'
			},
			tooltips: {
				mode: 'index',
				intersect: false,
			},
			hover: {
				mode: 'nearest',
				intersect: true
			},
			scales: {
				xAxes: [{
					display: true,
					scaleLabel: {
						fontSize: 14,
						display: true,
						labelString: 'Mes'
					}
				}],
				yAxes: [{
					display: true,
					scaleLabel: {
						fontSize: 14,
						display: true,
						labelString: 'Numero de citas'
					},
					ticks: {
						min: 0,
						stepSize: 1
					}
				}]
			}
		}
	});
}