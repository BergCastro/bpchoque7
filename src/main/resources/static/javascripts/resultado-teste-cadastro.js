var BpChoque = BpChoque || {};

BpChoque.ResultadoCadastro = (function() {

	function ResultadoCadastro() {
		this.modal = $('#modalAdicionaResultado');
		this.botaoSalvar = this.modal
				.find('.js-modal-cadastro-resultado-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputParticipante = $('#participante');
		this.inputQtdProvas = $('#qtdProvas');

		this.containerMensagemErro = $('.js-mensagem-cadastro-detalhe');
	}

	ResultadoCadastro.prototype.iniciar = function() {
		this.form.on('submit', function(event) {
			event.preventDefault()
		});
		this.modal.on('show.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}

	function onModalShow() {
		this.inputParticipante.focus();
		$('.input-prova').val('0');
		$('.js-time').val("00''00");
		$('.js-time-min').val("00'00");
		
	}

	function onModalClose() {
		//this.inputParticipante.val('');
		
		
		    // Also clear loaded content, otherwise it would flash before new one is loaded.
		
		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}

	function onBotaoSalvarClick() {
		var participante = this.inputParticipante.val().trim();

		var qtdProvas = this.inputQtdProvas.val();

		

		var inputValorProvas = [];
		var inputIdProvas = [];
		
		
		$('.input-prova').each(function() {
			
			var input = $(this).val();
			inputValorProvas.push(input);
		});
		$('.input-id-prova').each(function() {
			inputIdProvas.push($(this).val());
		});
		

		$.ajax({
			url : this.url,
			method : 'POST',
			contentType : 'application/json',
			data : JSON.stringify({
				pessoa : participante,
				provas : inputIdProvas,
				valores : inputValorProvas
			}),

			error : onErroSalvandoEstilo.bind(this),
			success : onEstiloSalvo.bind(this)
		});
	}

	function onErroSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}

	function onEstiloSalvo() {
		var corpoTabela = $('#resultsBlock');
		
		
		this.modal.modal('hide');
		
		//$("#resultsBlock").load('/testesFisicos/resultados');
		//$.getScript('/stylesheets/pesquisa/bootstrap-select.css');
		
		//$("#modaisBloco").load('/testesFisicos/atualizaModais');
		//$("#modaisBloco").css('/stylesheets/pesquisa/bootstrap-select.css');
		
		//$("#javascriptBloco").load('/testesFisicos/atualizaJavaScript');
		
				
		/*$.getScript('/layout/javascripts/vendors.min.js');
		$.getScript('/layout/javascripts/algaworks.min.js');
		$.getScript('/javascripts/vendors/jquery.masknumber.min.js');
		$.getScript('/javascripts/vendors/jquery.mask.min.js');
		$.getScript('/javascripts/vendors/bootstrap-datepicker.min.js');
		$.getScript('/javascripts/vendors/bootstrap-datepicker.pt-BR.min.js');
		$.getScript('/javascripts/vendors/numeral.min.js');
		$.getScript('/javascripts/vendors/pt-br.min.js');
		
		$.getScript('/javascripts/brewer.js');
		
		$.getScript('/javascripts/brewer.dialogo-excluir.js');
		
		$.getScript('/javascripts/vendors/uikit.min.js');
		$.getScript('/javascripts/provas-tipoteste-cadastro.js');
		$.getScript('/javascripts/resultado-teste-cadastro.js');
		
		$.getScript('/javascripts/bpchoque.js');
		$.getScript('/javascripts/pesquisa/bootstrap-select.js');
		$.getScript('/javascripts/formata-campo-tempo.js');*/
		
		
		
		location.reload();
	}

	return ResultadoCadastro;

}());

$(function() {
	var resultadoCadastro = new BpChoque.ResultadoCadastro();
	resultadoCadastro.iniciar();
	
	
});


