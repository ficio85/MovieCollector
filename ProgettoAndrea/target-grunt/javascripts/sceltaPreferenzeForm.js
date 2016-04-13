	function ProvinciaP (id, regione, provincia, insegnamenti) {
	    this.id = id;
		this.regione = regione;
	    this.provincia = provincia;
	    this.insegnamenti = insegnamenti;
	}


	function creaTabella(container, data, nome) {
		 
	    var table = $("<table/>").addClass('table table-bordered table-striped table fixedTableWidth');
	    table.attr('id', nome);
	    
	    $.each(data, function(rowIndex, r) {
	        var row = $("<tr/>");
	        $.each(r, function(colIndex, c) { 
	            row.append($("<t"+(rowIndex === 0 ?  "h" : "d")+"/>").html(c));
	        });
	        table.append(row);
	    });
	    return container.append(table);
	}

	function aggiungiRiga(table, rowData, provincia) {
		  
		  var lastRow = $('<tr/>').appendTo(table.find('tbody'));		  
		  lastRow.attr('id', provincia);		  
		  $.each(rowData, function(colIndex, c) { 		  	
		      lastRow.append($('<td/>').html(c));
		  });		  
		   
		  return lastRow;
	}
	
	

	function spostaRiga(cur, tableDestinazione, posizione, mappa) {
		 		
		  var provincia = $(cur).closest("tr").attr("id");	
		  var idRow = '#'+ provincia;		  		 
		  var lastRow = $('<tr/>').appendTo(tableDestinazione.find('tbody'));
		  var curProv = mappa[provincia];
		  var rowData;
		  
		  if(tableDestinazione.attr("id") === 'preferenzeID') {
		  	 rowData = ['' , curProv.provincia, curProv.regione, $('#tastoRimuovi').html(), $('#tastoSu').html(), $('#tastoGiu').html()];
			 		  
			  $.each(rowData, function(colIndex, c) { 				  
			      lastRow.append($('<td/>').html(c));			      
			  });
		  }	else {			  
			  rowData = [curProv.provincia, curProv.regione, $('#tastoAggiungi').html()];
			  
			  $.each(rowData, function(colIndex, c) {
				  lastRow.append($('<td/>').html(c));
			  });
		  }	  
		  
		  $(idRow).remove();
		  lastRow.attr('id', provincia);
		  
		  aggiornaPosizioni(posizione);
		  
		  return lastRow;
	}
	
	function spostaSu(cur, tableDestinazione, posizione) {
	
		var row = $(cur).parents("tr:first");
	
		 if(row.prev('tr').attr("id")) {
		 	row.insertBefore(row.prev());		 	
		 }  else {
		 	alert("Non è possibile spostare ulteriormente la selezione");
		 }
		 		
		  aggiornaPosizioni(posizione);	  
	}
	
	function spostaGiu(cur, tableDestinazione, posizione) {
	
		var row = $(cur).parents("tr:first");
		 
		  if(row.next('tr').attr("id")) {
		 	row.insertAfter(row.next());
		 }  else {
		 	alert("Non è possibile spostare ulteriormente la selezione");
		 }
		  
		 aggiornaPosizioni(posizione);
		 
	}
	    	
	
	function aggiornaPosizioni(posizione) {
		
		$('#preferenzeTable > tbody > tr').each(function() { 

			  $(this).find("td:nth-child(1)").html('<b>'+posizione+'</b>');
		      posizione++;
		  });

	}
		
	function inviaDatiPreferenze(size) {	
		
		$('#formPreferenze').unbind("submit");
		
		 $('#formPreferenze').submit(function() {
			 
			  var rows = $('#preferenzeTable tbody  tr').get();
			 
			 // Conta anche le intestazioni
			 if((rows.length) < size+1) {
			 	alert("In base alle disposizioni di cui al comma 100 della Legge, l'aspirante docente deve esprimere l'ordine di preferenza tra tutte le province, a livello nazionale. Finché le province selezionabili non saranno state tutte indicate, la domanda potrà essere salvata per una successiva rilavorazione, ma non potrà essere convalidata. Le domande non convalidate non partecipano al piano assunzionale straordinario a.s. 2015/16.");
			 	// return false;
			 } 
				
			 $('#formPreferenze').html('');
			 
			 var isSostegno = $("input[type='radio'][name='sostegno']").length>0;
			 			 
			 if(isSostegno) {
			 	 var selected = $("input[type='radio'][name='sostegno']:checked");
				 if (selected.length > 0) {
				     $('<input>').attr({
					    type: 'hidden',
					    name: 'sostegno',
					    value: selected.val()
					 }).appendTo($('#formPreferenze'));
				 } else {
					 $('<input>').attr({
						    type: 'hidden',
						    name: 'sostegnoError',
						    value: 'X'
					}).appendTo($('#formPreferenze'));
				 	alert("In base alle disposizioni di cui al comma 100 della Legge, l'aspirante docente in possesso di una o più specializzazioni di sostegno deve esprimere l'ordine di preferenza fra posti di sostegno e posti comuni.");
				 }
			 }
			 
			 $('<input>').attr({
				 type: 'hidden',
				 name: 'action'
		 	 }).appendTo($('#formPreferenze'));
		 	 
		 	 var testArray = [];
			 var testC = true;
		 	 			 
			 
			 $.each(rows, function(index, row) {
				 	if($(row).find("td").length) {
				 		
				 		$('<input>').attr({
				 		    type: 'hidden',
				 		    name: 'pos_'+ $(row).find("td:nth-child(1)").text(),
				 		    value: $(row).attr("id")
				 		}).appendTo($('#formPreferenze'));
				 	}
			 });	
			 
		 });
		 		 
		 $('#formPreferenze').submit();		
		
	}
		
	function ritornaDaPreferenze() {	
		 $('<input>').attr({
				 type: 'hidden',
				 name: 'action'
		 	 }).appendTo($('#formPreferenzeRitorna'));
		
		 $('#formPreferenzeRitorna').submit();	
	}
	
	function ordinaPerRegione(){
		
		  var rows = $('#cityTable tbody  tr').get();
		  
		  rows.sort(function(a, b) {

		  var A = $(a).find("td:nth-child(2)").text().toUpperCase();
		  var B = $(b).find("td:nth-child(2)").text().toUpperCase();

		  if(A < B) {
		    return -1;
		  }

		  if(A > B) {
		    return 1;
		  }

		  return 0;

		  });

		  $.each(rows, function(index, row) {
		    $('#cityTable').children('tbody').append(row);
		  });
	}
	
	function ordinaPerProvincia(){
		
		  var rows = $('#cityTable tbody  tr').get();

		  rows.sort(function(a, b) {

		  var A = $(a).find("td:nth-child(1)").text().toUpperCase();
		  var B = $(b).find("td:nth-child(1)").text().toUpperCase();

		  if(A < B) {
		    return -1;
		  }

		  if(A > B) {
		    return 1;
		  }

		  return 0;

		  });
		  
		  $.each(rows, function(index, row) {
		    $('#cityTable').children('tbody').append(row);
		  });
	}
		
	function ordinaPer(ordine) {
		if(ordine) {
			if(ordine==='PRO') {
				ordinaPerProvincia();
			} else {
				ordinaPerRegione();
			}
		} else {
			ordinaPerRegione();
		}
	}
	

	
	