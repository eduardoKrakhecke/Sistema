
app.controller("listaClienteController", function ($scope, $http, $q,usuario, endereco, cliente) {
    var vm = this;

    vm.pessoa={};
    vm.pessoas=[];
    vm.tituloPanel="Clientes";
    vm.quantidaDeRegistrosPorPagina="5";
    vm.pagina = 0;
    vm.filtro = "";
    vm.quantidadeDeRegistros = 0;
    vm.ultimaPagina = false;
    vm.primeiraPagina = false;
    vm.ultimoRegistroDaPagina = 0;

    vm.proximaPagina = function() {
        vm.pagina++;
        vm.carregarClientes();
    };

    vm.paginaAnterior = function() {
        vm.pagina--;
        vm.carregarClientes();
    };

    vm.filtrar = function(){
        vm.carregarClientes();
    };

    vm.carregarClientes=function(zerarPaginacao){
        if (zerarPaginacao){
            vm.pagina = 0;
        }
        cliente.getPessoasPaginadas(vm.pagina, parseInt(vm.quantidaDeRegistrosPorPagina), vm.filtro ).then(function (retorno) {
            vm.pessoas = retorno.conteudo;
            vm.quantidadeDeRegistros = retorno.quantidadeDeRegistros;
            vm.ultimaPagina = retorno.ultimaPagina;
            vm.primeiraPagina = retorno.primeiraPagina;
            vm.ultimoRegistroDaPagina = vm.pagina * vm.quantidadeDeRegistros + parseInt(vm.quantidaDeRegistrosPorPagina);
            if (vm.ultimoRegistroDaPagina > vm.quantidadeDeRegistros) {
                vm.ultimoRegistroDaPagina = vm.quantidadeDeRegistros;
            }
        });
    };

    vm.carregarClientes();
});