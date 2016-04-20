

$(function(){
	
	
	    $( "#actors" ).autocomplete({
	    	source: function( request, response ) {

	    		$.ajax({
	    			url: "../ProgettoAndrea/loadActors",
	    			data:"stringActor="+request.term,
	    			success: function( data ) {
	    				response(data);
	    			},
	    			error: function(XMLHttpRequest, textStatus, errorThrown) { 
	    				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
	    			} 
	    		});
	    	},
	    	minLength: 3,
	    });
	
	    $( "#actors2" ).autocomplete({
	    	source: function( request, response ) {

	    		$.ajax({
	    			url: "../ProgettoAndrea/loadActors",
	    			data:"stringActor="+request.term,
	    			success: function( data ) {
	    				response(data);
	    			},
	    			error: function(XMLHttpRequest, textStatus, errorThrown) { 
	    				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
	    			} 
	    		});
	    	},
	    	minLength: 3,

	    });
});