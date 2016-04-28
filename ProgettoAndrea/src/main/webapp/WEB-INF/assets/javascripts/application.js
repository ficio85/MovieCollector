

$(function(){
	
	    $( ".autoCompleteClass" ).autocomplete({
	    	source: function( request, response ) {
	    		var link=$('#contextPath').val()+"/loadActors";
	    		$.ajax({
	    			url: link,
	    			data:"stringActor="+request.term,
	    			success: function( data ) {
	    				var url="/loadActors";

	    				response(data);
	    			},
	    			error: function(XMLHttpRequest, textStatus, errorThrown) { 
	    				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
	    				alert(link);
	    			} 
	    		});
	    	},
	    	minLength: 3,
	    });
	
	    $( "autoCompleteClass" ).autocomplete({
	    	source: function( request, response ) {
	    		var link=$('#contextPath').val()+"/loadDirectors";
	    		$.ajax({
	    			url: link,
	    			data:"stringDirector="+request.term,
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