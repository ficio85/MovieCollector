

$(function(){



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

	$( "#labels" ).autocomplete({
		source: function( request, response ) {
			var link=$('#contextPath').val()+"/loadLabels";
			$.ajax({
				url: link,
				data:"stringLabel="+request.term,
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
				"			<label for=\"genre\" class=\"col-md-2 control-label\">Genre "+(contGenre+1)+":</label>                                               "+
				"			<div class=\"col-md-6\">                                                                                            "+
				"				<select id=\"genere"+contGenre+"\" name=\"genere\" class=\"form-control \">                                                 "+
				"				</select>	                                                                                                    "+
				"									                                                                                            "+
				"			</div>                                                                                                              "+
				"			<div class=\"col-md-2 plusPadding\">                                                                                "+
				"					<button type=\"button\" class=\"btn btn-info emptyGenreClass btn-sm\" id=\"resetGenre"+contGenre+"\"><span class=\"fa fa-undo fa-1x\"></span></button>     "+	    			
				"					<button type=\"button\" class=\"btn btn-info addGenreClass btn-sm\" id=\"addGenre"+contGenre+"\"><span class=\"fa fa-plus fa-1x\"></span></button>     "+
				"			</div>                                                                                                              "+
				"</div>                                                                                                                          "+
		"		<div id=\"replaceGenre\"></div>");
		$('#genere0 option').clone().appendTo('#genere'+contGenre);
		if(contGenre===2)
		{
			$("#addGenre"+contGenre).prop("disabled",true);
		}
	});


	//searchMovie jquery
	$("#search-by-title-form").on("click",".addActorClass",function(event ){
		event.preventDefault();
		var  varCurrentCont=parseInt($("#contActor").val());
		if(varCurrentCont===0)
		{
			$( "#unisciAttori" ).append("	<div class=\"col-md-2 control-label text-right\">                                       "+
					"			<input type=\"checkbox\" name=\"unisciAttori\" value=\"1\">             "+
					"		</div>                                                                      "+
					"		<div class=\"col-md-5 control-label-left\">                            "+
					"				Unisci gli attori                                                     "+
			"		</div>                                                                      ");
		}
		$("#addActor"+varCurrentCont).remove();
		var contActor= varCurrentCont+1;
		$("#contActor").val(contActor);
		$( "#replaceActor" ).replaceWith( "<div class=\"form-group\">                                                                                                      "+
				"			<label for=\"actor\" class=\"col-md-2 control-label\">Actor "+(contActor+1)+":</label>                                               "+
				"	<div class=\"col-md-6\">                                                                    "+
				"			<input type=\"text\" class=\"autoCompleteActorRender form-control\"                  "+
				"				data-servlet=\"/loadActors\" name=\"actor\" id=\"actor"+contActor+"\"></form:input>         "+
				"		</div>																					"+	    				    			
				"			<div class=\"col-md-2 plusPadding\">                                                                                "+
				"					<button type=\"button\" class=\"btn btn-info addActorClass btn-sm\" id=\"addActor"+contActor+"\"><span class=\"fa fa-plus fa-1x\"></span></button>     "+
				"			</div>                                                                                                              "+
				"</div>                                                                                                                          "+
		"		<div id=\"replaceActor\"></div>");
		if(contActor===2)
		{
			$("#addActor"+contActor).prop("disabled",true);
		}
	});


	$("#search-by-title-form").on("click",".addLabelClass",function(event ){
		event.preventDefault();
		var  varCurrentCont=parseInt($("#contLabel").val());
		if(varCurrentCont===0)
		{
			$( "#unisciLabel" ).append("	<div class=\"col-md-2 control-label text-right\">                                       "+
					"			<input type=\"checkbox\" name=\"unisciLabel\" value=\"1\">             "+
					"		</div>                                                                      "+
					"		<div class=\"col-md-5 control-label-left\">                            "+
					"				Unisci le label                                                     "+
			"		</div>                                                                      ");
		}
		$("#addLabel"+varCurrentCont).remove();
		var contLabel= varCurrentCont+1;
		$("#contLabel").val(contLabel);
		$( "#replaceLabel" ).replaceWith( "<div class=\"form-group\">                                                                                                      "+
				"			<label for=\"actor\" class=\"col-md-2 control-label\"> Label "+(contLabel+1)+":</label>                                               "+
				"	<div class=\"col-md-6\">                                                                    "+
				"			<input type=\"text\" class=\"autoCompleteLabelRender form-control\"                  "+
				"				data-servlet=\"/loadLabels\" name=\"label\" id=\"label"+contLabel+"\"></form:input>         "+
				"		</div>																					"+	    				    			
				"			<div class=\"col-md-2 plusPadding\">                                                                                "+
				"					<button type=\"button\" class=\"btn btn-info addLabelClass btn-sm\" id=\"addLabel"+contLabel+"\"><span class=\"fa fa-plus fa-1x\"></span></button>     "+
				"			</div>                                                                                                              "+
				"</div>                                                                                                                          "+
		"		<div id=\"replaceLabel\"></div>");
		if(contLabel===2)
		{
			$("#addLabel"+contLabel).prop("disabled",true);
		}
	});






	$("#search-by-title-form").on("click",".emptyGenreClass",function(event ){
		event.preventDefault();
		var attrId=$(this).attr('id');
		var index=parseInt(attrId.substring(attrId.length-1));
		alert("#select"+index);
		$('#genere'+index).val('').prop('selected',true);
	});

	$("#search-by-title-form").on("keydown.autocomplete",".autoCompleteActorRender",function(event ){ $( this).autocomplete({
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
	});});


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
				alert("movie");
				$("#movieResult .ratingDisplay").rating({min:0, max:10, step:0.5, stars:10, size:'xs'});

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
	//detail.jsp


	//generate label
	$("#createLabel").click(function(event){
		event.preventDefault();
		var labels = $("#labelSpace").find(".label"); 
		if (labels.size()===5)
		{
			$(this).prop("disabled",true);
		}
		else
		{
			var textLabel=$("#labelText").val();
			var styleLabel=generateLabelColor();
			var htmlToAppend="<div class=\"col-md-2 p-t-md classToCancel\" >";
			htmlToAppend +="<div class=\"label "+styleLabel+" lb-sm\">"+textLabel+"</div>";
			htmlToAppend += "<i class=\"icon icon-circle-with-cross removeLabel\"></i>";
			htmlToAppend += "</div> ";
			$("#labelSpace").append(htmlToAppend);
			if (labels.size===5)
			{
				$(this).prop("disabled",true);
			}
		}


	});




	$("#submitLabels").click(function(event){
		event.preventDefault();
		var labels = $("#labelSpace").find(".label"); 
		var labelArray=[];
		var indexMovie=$("#key").data("id");
		var i=0;
		labels.each(function(i) {
			labelArray.push($(this).text());
		});
		var link=$('#contextPath').val()+"/insertLabel";
		$.ajax({
			url: link,
			type:"POST",
			dataType:'json',
			data: {labels:labelArray,indexMovie:indexMovie},
			success: function( data ) {
				alert("ok");
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			} 
		});

	});

	$("#submitRate").click(function(event){
		event.preventDefault();
		var rateInt = $("#rateInt").val();
		var rateDec=$("#rateDec").val();
		var labelArray=[];
		var indexMovie=$("#key").data("id");

		var link=$('#contextPath').val()+"/insertRate";
		$.ajax({
			url: link,
			type:"POST",
			dataType:'json',
			data: {rateInt:rateInt,rateDec:rateDec,indexMovie:indexMovie},
			success: function( response ) {
				if(response.rateChanged===true)
				{
					$("#starContainer").html(
							"<i class=\"fa fa-star fa-stack-2x "+getStarClass(response.newRate)+"\"></i>"+
							"<i class=\"fa fa-stack-1x star-text-render\">"+response.newRateString+"</i>	"
					);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			} 
		});

	});


	$("#submitRateActor").click(function(event){
		event.preventDefault();
		var rateInt = $("#rateInt").val();
		var rateDec=$("#rateDec").val();
		var labelArray=[];
		var indexMovie=$("#key").data("id");

		var link=$('#contextPath').val()+"/insertRateActor";
		$.ajax({
			url: link,
			type:"POST",
			dataType:'json',
			data: {rateInt:rateInt,rateDec:rateDec,indexMovie:indexMovie},
			success: function( response ) {
				if(response.rateChanged===true)
				{
					$("#starContainer").html(
							"<i class=\"fa fa-star fa-stack-2x "+getStarClass(response.newRate)+"\"></i>"+
							"<i class=\"fa fa-stack-1x star-text-render\">"+response.newRateString+"</i>	"
					);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			} 
		});

	});

	$("#submitRateDirector").click(function(event){
		event.preventDefault();
		var rateInt = $("#rateInt").val();
		var rateDec=$("#rateDec").val();
		var labelArray=[];
		var indexDirector=$("#key").data("id");

		var link=$('#contextPath').val()+"/insertRateDirector";
		$.ajax({
			url: link,
			type:"POST",
			dataType:'json',
			data: {rateInt:rateInt,rateDec:rateDec,indexDirector:indexDirector},
			success: function( response ) {
				if(response.rateChanged===true)
				{
					$("#starContainer").html(
							"<i class=\"fa fa-star fa-stack-2x "+getStarClass(response.newRate)+"\"></i>"+
							"<i class=\"fa fa-stack-1x star-text-render\">"+response.newRateString+"</i>	"
					);
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			} 
		});

	});
	
	
	
	
	
	
	$("#labelSpace").on("click",".removeLabel",function(){

		$(this).parent(".classToCancel").remove();
		if($("#labelSpace").find(".label").size()===4)
		{
			$("#createLabel").prop("disabled",false);
		}



	});

	function getStarClass( rank) {
		// TODO Auto-generated method stub
		var render="";

		if(rank<3)
		{
			render+="star-black-render ";
		}
		else if ( rank>=3 && rank<6)
		{
			render+="star-red-render ";
		}
		else if(rank>=6 && rank <8)
		{
			render+="star-green-render ";
		}
		else if(rank>=8)
		{
			render+="star-yellow-render ";
		}
		return render;

	}

	function generateLabelColor()
	{
		var rand =Math.floor((Math.random() * 5) + 1);
		var render;
		switch (rand) { 
		case 1: 
			render= "label-primary ";break;
		case 2: 
			render= "label-success";	break;    			
		case 3: 
			render= "label-info";break;	    					
		case 4: 
			render= "label-warning";break;
		case 5: 
			render= "label-danger";break;
		default: render= "label-default";
		}
		return render;
	}

	//rating
	$('#starinput-detail').on('rating.hover', function(event, value, caption) {
		var valueInString=""+value;
		var values = valueInString.split(".");
		$("#rateInt").val(values[0]);
		$("#rateDec").val(values[1]);
	});

	//movieDetail

	$('.actorList').click(
			function(event){
				var actor = $(this).html();
				
			}

	);

});