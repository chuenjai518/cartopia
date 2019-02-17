$(document).ready(function(){
	$('#CarParkOwner').hide();
	$('#userType').change(function() {
	    $('#' + $(this).val()).show();
	    console.log('#' + $(this).val());
	    if($(this).val() == "CarParkOwner"){
	    	$('#member').hide();
	    }else{
	    	$('#CarParkOwner').hide();
	    }
	});
});
