

$(function(){
	$( "#actors" ).autocomplete({
		source: "${pageContext.request.contextPath}/loadActors",
		minLength: 2,
		select: function( event, ui ) {}
	});

});