
app.controller("listaClienteController", function ( clienteService, mensagem, risco,$filter) {
    var vm = this;
    vm.cliente={};
    vm.clientes=[];
    vm.tituloPanel="Clientes";
    vm.seleciona= [];
    vm.riscos = [];

    risco.getRisco().then(function (retorno) {
        vm.riscos = retorno;
    });

    vm.carregarClientes=function( ){
        clienteService.getCliente().then(function (retorno) {
            vm.clientes = retorno;
        });
    };


    vm.salvarCliente = function (formCliente) {
        clienteService.salvar(vm.cliente).then(function (retorno) {
            mensagem.mensagemSucesso("Registro salvo com sucesso");
            vm.limparDadosModal(formCliente);
            vm.carregarClientes();
        });

    };

   vm.limparDadosModal = function (formCliente){
       vm.cliente = {};
       formCliente.$setUntouched();
   };

    vm.alterar = function(cliente){
        vm.cliente = angular.copy(cliente);
    };



    vm.riscoSelecionado = function(idRisco){
        var selecionado = $filter('filter')(vm.riscos, {idRisco: idRisco}, true)[0];
        vm.cliente.risco.taxaJuro = selecionado.taxaJuro;
    };


    vm.excluir = function(cliente){
        mensagem.mensagemConfirmacaoExclusao(cliente.nome,function (result) {
            if(result) {
                clienteService.excluir(cliente.idCliente).then(function (retorno){
                    mensagem.mensagemSucesso("Registro excluído com sucesso");
                    vm.carregarClientes();
                });
            }
        });
    };

    vm.carregarClientes();

});