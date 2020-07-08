$(document).ready(function(){
	
	mostrar_registros(1,"");
	
	$("#busqueda_input").keyup(function(){
		   mostrar_registros($("#opciones").val(),$(this).val());
	   });

});

function mostrar_registros(opcion,busqueda){
	
	$.post("../../Compras",{
		accion : "mostrar_compras",
		valor : opcion,
		search: busqueda
	},
	function(responseJson){
		$("tbody").empty();
		var datos = JSON.parse(responseJson);
		
		var suma=0;
		
		const options2 = { style: 'currency', currency: 'USD' };
		const numberFormat2 = new Intl.NumberFormat('en-US', options2);
		var date;
		var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
		
		for(var i in datos){
			date = new Date(datos[i].Fecha);
			date.setDate(date.getDate() + 1);
			$("#tbody1").append("<tr id=\"R"+datos[i].Codigo+"\" onclick=\"extender("+datos[i].Codigo+")\" class=\"odd\">" +
					"<td>"+datos[i].Codigo+"</td>" +
					"<td>"+datos[i].Nombre+"</td>" +
					"<td>"+date.toLocaleDateString("es-ES", options)+"</td>" +
					"<td>"+datos[i].Hora[0]+datos[i].Hora[1]+datos[i].Hora[2]+datos[i].Hora[3]+datos[i].Hora[4]+"</td>" +
					"<td> "+numberFormat2.format(datos[i].Total)+"</td>"+
					
					"<td>"+
					"<button type=\"button\" class=\"editar_pro but_"+datos[i].Codigo+"\"> <img width=\"25px\"  alt=\"icono-mas-info\" src=\"../img/mas-icono.svg\"></button>"+
					"<button type=\"button\" class=\"editar_pro bt-menos but_"+datos[i].Codigo+"\"> <img width=\"25px\"  alt=\"icono-menos-info\" src=\"../img/menos-icono.svg\"></button>"+
					"</td>"+
	                "</tr>");
			suma+=parseInt(datos[i].Total,10);
			
			$("#tbody1").append("<tr> <td colspan=\"6\">" +
					"<table>"+
	                			"<thead>"+
	                			"<tr>"+
	                		    "<th>Codigo</th>"+
	                		    "<th>Nombre</th>"+
	                		    "<th>Cantidad</th>"+
	                		    "<th>Precio Unitario</th>"+
	                		    "<th>Precio Total</th>"+
	                		    "</tr>"+
	                			"</thead>" +
	                			"<tbody id=\"Tb"+datos[i].Codigo+"\""+
	                			"</tbody>"+
	                	"</table>" +
	                	"</tr>");
			var codigoT="#Tb"+datos[i].Codigo;
			
			for(j in datos[i].Compras){
				$(codigoT).append("<tr class=\"odd\" >" +
						"<td>"+datos[i].Compras[j][0]+"</td>" +
						"<td>"+datos[i].Compras[j][1]+"</td>" +
						"<td>"+datos[i].Compras[j][2]+"</td>" +
						"<td>"+numberFormat2.format(datos[i].Compras[j][3])+"</td>" +
						"<td> "+numberFormat2.format(datos[i].Compras[j][3]*datos[i].Compras[j][2])+"</td>"+
						"</tr>");
			}
			
		}
		
		
		$("#tbody1").append("<tr class=\"odd\">" +
				"<td></td><td></td>" +
				"<td></td>" +
				"<td> Total </td>" +
				"<td> "+numberFormat2.format(suma)+"</td>" +
				"<td></td>"+
	            "</tr>");
		
		$("#reporte tr:not(.odd)").hide();
	    $("#reporte tr:first-child").show();
	});
}

$(document).ready(function () {  
    var form = $('.form'),  
     cache_width = form.width(),  
     a4 = [595.28, 841.89]; // for a4 size paper width and height  

    $('#create_pdf').on('click', function () {  
        $('body').scrollTop(0);  
        createPDF();  
    });  
    //create pdf  
    function createPDF() {  
        getCanvas().then(function (canvas) {  
            var  
             img = canvas.toDataURL("image/png"),  
             doc = new jsPDF({  
                 unit: 'px',  
                 format: 'a4'  
             });  
            doc.addImage(img, 'JPEG', 20, 20);  
            doc.save('ComprasPDF.pdf');  
            form.width(cache_width);  
        });  
    }  

    // create canvas object  
    function getCanvas() {  
        form.width((a4[0] * 1.33333) - 80).css('max-width', 'none');  
        return html2canvas(form, {  
            imageTimeout: 2000,  
            removeContainer: true  
        });  
    }  
});