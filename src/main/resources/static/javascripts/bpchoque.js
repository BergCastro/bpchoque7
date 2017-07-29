$('#myModalExclusao').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var cargoId = button.data('id');
	var cargoNome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	var tipo = form.data('tipo');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + cargoId);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir '+tipo+'<strong>' + cargoNome + '</strong>?');
});

$('#myModalExclusaoDetalhe').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var detalheId = button.data('id');
	var detalheNome = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	var tipo = form.data('tipo');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + detalheId);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir '+tipo+'<strong>' + detalheNome + '</strong>?');
});

$('#myModalExclusaoProva').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var detalheId = button.data('id');
	var detalheNome = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	var tipo = form.data('tipo');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + detalheId);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir '+tipo+'<strong>' + detalheNome + '</strong>?');
//location.reload();
});
$('#myModalExclusaoTipo').on('show.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var detalheId = button.data('id');
	var detalheNome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	var tipo = form.data('tipo');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + detalheId);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir '+tipo+'<strong>' + detalheNome + '</strong>?');
//location.reload();
});

$('#myModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	 
	
});

$('#ModalSignUp').click(function(){
    $('#myModal').load('/AtualizaResultado');
});