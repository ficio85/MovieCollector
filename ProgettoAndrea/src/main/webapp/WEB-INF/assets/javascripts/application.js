

$(function(){
	
	function split( val ) {
	      return val.split( /,\s*/ );
	    }
	    function extractLast( term ) {
	      return split( term ).pop();
	    }
	
	
	$( "#actors" ).autocomplete({
		source: function( request, response ) {
			var search=$("#actors").val();
			var prova=
				$.ajax({
					url: "../ProgettoAndrea/loadActors",
					headers: { 
					    Accept : "application/json"
					},
					data:"stringActor="+request.term,
					success: function( data ) {
						console.log('prova');
						response( data );
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					} 
				});
			console.log(prova);
		},
	        search: function() {
	            // custom minLength
	            var term = extractLast( this.value );
	            if ( term.length < 2 ) {
	              return false;
	            }
	          },
	          focus: function() {
	            // prevent value inserted on focus
	            return false;
	          },
	          select: function( event, ui ) {
	            var terms = split( this.value );
	            // remove the current input
	            terms.pop();
	            // add the selected item
	            terms.push( ui.item.value );
	            // add placeholder to get the comma-and-space at the end
	            terms.push( "" );
	            this.value = terms.join( ", " );
	            return false;
	          }
	});
	

});