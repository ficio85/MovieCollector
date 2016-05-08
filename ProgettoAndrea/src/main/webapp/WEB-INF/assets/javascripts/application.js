

$(function(){
	
	    $( "#actors" ).autocomplete({
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
	
	    $( "#directors" ).autocomplete({
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
	    //searchMovie jquery
	    $("#search-by-title-form").on("click",".addGenreClass",function(event ){
	    	  event.preventDefault();
	    	 var  varCurrentCont=parseInt($("#contGenre").val());
	    	 if(varCurrentCont===0)
	    		 {
	    		 $( "#unisciGeneri" ).append("	<div class=\"col-md-2 control-label text-right\">                                       "+
	    			"			<input type=\"checkbox\" name=\"unisciGeneri\" value=\"1\">             "+
	    			"		</div>                                                                      "+
	    			"		<div class=\"col-md-5 control-label-left\">                            "+
	    			"				Unisci i generi                                                     "+
	    			"		</div>                                                                      ");
	    		 }
	    	  $("#addGenre"+varCurrentCont).remove();
	    	  var contGenre= varCurrentCont+1;
	    	  $("#contGenre").val(contGenre);
	    	$( "#replaceGenre" ).replaceWith( "<div class=\"form-group\">                                                                                                      "+
	    			"			<label for=\"genre\" class=\"col-md-2 control-label\">Genre "+contGenre+"</label>                                               "+
	    			"			<div class=\"col-md-6\">                                                                                            "+
	    			"				<select id=\"genere"+contGenre+"\" name=\"genere\" class=\"form-control \">                                                 "+
	    			"					<option value=\"\">--Select Genre--</option>                                                                "+
	    			"				</select>	                                                                                                    "+
	    			"									                                                                                            "+
	    			"			</div>                                                                                                              "+
	    			"			<div class=\"col-md-2 plusPadding\">                                                                                "+
	    			"					<button type=\"button\" class=\"btn btn-info addGenreClass btn-sm\" id=\"addGenre"+contGenre+"\"><span class=\"fa fa-plus fa-1x\"></span></button>     "+
	    			"			</div>                                                                                                              "+
	    			"</div>                                                                                                                          "+
	    			"		<div id=\"replaceGenre\"></div>");
	    	$('#genere option').clone().appendTo('#genere'+contGenre);
	    });
	    $("#search-by-title-form").submit(function(){
	    		event.preventDefault();
	    		var form=$(this).serialize();
	    		var link=$('#contextPath').val()+"/searchMovieResult";
	    		$.ajax({
	    			url: link,
	    			data:form,
	    			success: function( data ) {
	    				$("#movieResult").html(data);
	    				generatePagination();
	    			},
	    			error: function(XMLHttpRequest, textStatus, errorThrown) { 
	    				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
	    			} 
	    		});
	    });
	    
	    
	    
	    //result movie jquery
	    $("#movieResult").on("click",".social",function(){
	    	var index = $(this).data("index");
	    	$(this).siblings("[data-index]").removeClass( "btn-primary").addClass( "btn-default-outline" );
    		$( this ).removeClass( "btn-default-outline").addClass( "btn-primary" )	 ;  
    		$('#starinput-'+index).rating('update', 3);    	
	    });
	    
	    $(".social").click( function(){
	    	var index = $(this).data("index");
	    	$(this).siblings("[data-index]").removeClass( "btn-primary").addClass( "btn-default-outline" );
    		$( this ).removeClass( "btn-default-outline").addClass( "btn-primary" )	 ;  
    		$('#starinput-'+index).rating('update', 3);    		 
  
	    });
	    
	    function generatePagination(){
	    	var numPages=parseInt($("#numPages").val());
	    	 $('#pagination-movie').twbsPagination({
	    	        totalPages: numPages,
	    	        visiblePages: 10,
	    	        initiateStartPageClick :false,
	    	        onPageClick: function (event, page) {
	    	        	alert('click');
	    	            loadNewPage(page);
	    	        }
	    	    });
	    	
	    }
	    
   
	    function loadNewPage(page){
	    	$("#curPage").val(page);
	    	var form=$('#hiddenFormSearch').serialize();
    		var link=$('#contextPath').val()+"/searchMovieResult";
    		$.ajax({
    			url: link,
    			data:form,
    			success: function( data ) {
    				$("#elencoMovie").html(data);
    			},
    			error: function(XMLHttpRequest, textStatus, errorThrown) { 
    				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
    			} 
    		});
	    	
	    }
});