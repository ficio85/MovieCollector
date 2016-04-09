

$(function(){
	$('#primoRadioButton').attr('checked', true);
	$('#selezionaCon').prop('disabled',true);
	$('#confermaAccettazione').prop('disabled',true);
	$('#accetta').change(function(){
		if($(this).is(":checked")){
		$('#confermaAccettazione').prop('disabled',false);			
		}
		else{
				$('#confermaAccettazione').prop('disabled',true);			
		
		}
		});
	$('#primoRadioButton').change(function(){
		if($(this).is(":checked")){
			$('#selezionaGae').prop('disabled',false);
			$('#selezionaCon').prop('disabled',true);
			
		}
		});
	$('#secondoRadioButton').change(function(){
		if($(this).is(":checked")){
			$('#selezionaCon').prop('disabled',false);
			$('#selezionaGae').prop('disabled',true);
			
		}
		});
});