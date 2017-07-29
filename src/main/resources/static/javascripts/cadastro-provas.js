$(function () {
    var inputs  = $('.referencia');
    var linhaRefIdade = $('.referencia-idade');
    var linhaTentativa = $('.tentativa');
    var tipo = $('#tipo');
    var valorInputs = [];
    var refInicial = $('#refInicialMasc');
    
    linhaRefIdade.addClass('hide');
	linhaTentativa.addClass('hide');
	
    
    if(tipo.val() == 'APTOINAPTO'){
    	
    	linhaRefIdade.addClass('hide');
    	linhaTentativa.addClass('hide');
    	
    	
    }
    if(tipo.val() == 'TENTATIVA'){
    	linhaRefIdade.addClass('hide');
    	linhaTentativa.removeClass('hide');
    	//labelRef.addClass('hide');
    	//inputsIdades.addClass('hide');
    }
    if(tipo.val() == 'TEMPO_MIN'){
    	linhaTentativa.addClass('hide');
    	linhaRefIdade.removeClass('hide');
    	
    	inputs.addClass('js-time-min');
   	 	$.getScript('/javascripts/formata-campo-tempo.js');
    }
    if(tipo.val() == 'TEMPO'){
    	linhaTentativa.addClass('hide');
    	linhaRefIdade.removeClass('hide');
    	inputs.addClass('js-time');
   	 	$.getScript('/javascripts/formata-campo-tempo.js');
    }
    if(tipo.val() == 'INTEIRO'){
    	linhaTentativa.addClass('hide');
    	linhaRefIdade.removeClass('hide');
    	
   	 	
    }
    
  
    
    $('#tipo').on('change', function (event) {
    	
    	
        if(tipo.val() == 'TEMPO'){
        	linhaRefIdade.removeClass('hide');
        	inputs.removeClass('js-time-min');
        	
        	inputs.addClass('js-time');
        	linhaTentativa.addClass('hide');
        	
        	
        }else if(tipo.val() == 'TEMPO_MIN'){
        	linhaRefIdade.removeClass('hide');
        	inputs.removeClass('js-time');
        	
        	inputs.addClass('js-time-min');
        	linhaTentativa.addClass('hide');
        	$.getScript('/javascripts/formata-campo-tempo.js');
        	
        }else if(tipo.val() == 'INTEIRO'){
        	linhaRefIdade.removeClass('hide');
        	
        	inputs.removeClass('js-time-seg');
        	inputs.removeClass('js-time');
        	
        	linhaTentativa.addClass('hide');
        	
        	
        }else if(tipo.val() == 'APTOINAPTO'){
        	inputs.removeClass('js-time-seg');
        	inputs.removeClass('js-time');
        	
        	linhaRefIdade.addClass('hide');
        	linhaTentativa.addClass('hide');
        	
        	
        }else if(tipo.val() == 'TENTATIVA'){
        	inputs.removeClass('js-time-seg');
        	inputs.removeClass('js-time');
        	
        	linhaTentativa.removeClass('hide');
        	
        	linhaRefIdade.addClass('hide');
        	
        	
        	
        }else{
        	linhaRefIdade.addClass('hide');
        	linhaTentativa.addClass('hide');
        }
        
        
        inputs.val('');
        $.getScript('/javascripts/formata-campo-tempo.js');
       
        console.log(tipo.val())
    });

});