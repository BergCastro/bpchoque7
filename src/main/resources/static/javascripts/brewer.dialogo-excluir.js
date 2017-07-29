Brewer = Brewer || {};

Brewer.DialogoExcluir = (function() {
	
	function DialogoExcluir() {
		this.exclusaoBtn = $('.js-exclusao-btn')
	}
	
	DialogoExcluir.prototype.iniciar = function() {
		this.exclusaoBtn.on('click', onExcluirClicado.bind(this));
		
		if (window.location.search.indexOf('excluido') > -1) {
			swal('Pronto!', 'Excluído com sucesso!', 'success');
		}
	}
	
	function onExcluirClicado(evento) {
		event.preventDefault();
		var botaoClicado = $(evento.currentTarget);
		var url = botaoClicado.data('url');
		var objeto = botaoClicado.data('objeto');
		
		swal({
			title: 'Tem certeza?',
			text: 'Excluir ' + objeto + '? Você não poderá recuperar depois.',
			showCancelButton: true,
			confirmButtonColor: '#DD6B55',
			confirmButtonText: 'Sim, exclua agora!',
			closeOnConfirm: true
		}, onExcluirConfirmado.bind(this, url));
	}
	
	function onExcluirConfirmado(url) {
		$.ajax({
			url: url,
			method: 'DELETE',
			success: onExcluidoSucesso.bind(this),
			error: onErroExcluir.bind(this),
			complete: atualizaScript.bind(this)
		});
	}
	
	function onExcluidoSucesso() {
		/*var urlAtual = window.location.href;
		var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
		var novaUrl = urlAtual.indexOf('excluido') > -1 ? urlAtual : urlAtual + separador + 'excluido';
		
		window.location = novaUrl;*/
		//$("#resultsBlock").load('/testesFisicos/resultados');
		//$("#javascriptBloco").load('/testesFisicos/atualizaJavaScript');
		location.reload();
		
	}
	
	function atualizaScript() {
		
		/*$.getScript('/layout/javascripts/vendors.min.js');
		$.getScript('/layout/javascripts/algaworks.min.js');
		$.getScript('/javascripts/vendors/jquery.masknumber.min.js');
		$.getScript('/javascripts/vendors/jquery.mask.min.js');
		$.getScript('/javascripts/vendors/bootstrap-datepicker.min.js');
		//$.getScript('/javascripts/vendors/bootstrap-datepicker.pt-BR.min.js');
		//$.getScript('/javascripts/vendors/numeral.min.js');
		//$.getScript('/javascripts/vendors/pt-br.min.js');
		
		$.getScript('/javascripts/brewer.js');
		
		$.getScript('/javascripts/brewer.dialogo-excluir.js');
		
		$.getScript('/javascripts/vendors/uikit.min.js');
		$.getScript('/javascripts/provas-tipoteste-cadastro.js');
		$.getScript('/javascripts/resultado-teste-cadastro.js');
		
		$.getScript('/javascripts/bpchoque.js');
		$.getScript('/javascripts/pesquisa/bootstrap-select.js');
		$.getScript('/javascripts/formata-campo-tempo.js');*/
	}
	
	function onErroExcluir(e) {
		console.log('ahahahah', e.responseText);
		swal('Oops!', e.responseText, 'error');
	}
	
	return DialogoExcluir;
	
}());

$(function() {
	var dialogo = new Brewer.DialogoExcluir();
	dialogo.iniciar();
});
