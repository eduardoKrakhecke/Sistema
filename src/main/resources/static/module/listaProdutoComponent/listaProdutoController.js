
app.controller("listaProdutoController", function ($scope, $http, $q,usuario, produto, $rootScope) {
    var vm = this;

    vm.produto={};
    vm.produtos=[];
    vm.tituloPanel="Produtos";
    vm.quantidaDeRegistrosPorPagina="5";
    vm.pagina = 0;
    vm.filtro = "";
    vm.quantidadeDeRegistros = 0;
    vm.ultimaPagina = false;
    vm.primeiraPagina = false;
    vm.ultimoRegistroDaPagina = 0;

    vm.proximaPagina = function() {
        vm.pagina++;
        vm.carregarProdutos();
    };

    vm.paginaAnterior = function() {
        vm.pagina--;
        vm.carregarProdutos();
    };

    vm.filtrar = function(){
        vm.carregarProdutos();
    };

    vm.carregarProdutos=function(zerarPaginacao){
        if (zerarPaginacao){
            vm.pagina = 0;
        }
        produto.getProdutoPaginado(vm.pagina, parseInt(vm.quantidaDeRegistrosPorPagina), vm.filtro ).then(function (retorno) {
            vm.produtos = retorno.conteudo;
            vm.quantidadeDeRegistros = retorno.quantidadeDeRegistros;
            vm.ultimaPagina = retorno.ultimaPagina;
            vm.primeiraPagina = retorno.primeiraPagina;
            vm.ultimoRegistroDaPagina = vm.pagina * vm.quantidadeDeRegistros + parseInt(vm.quantidaDeRegistrosPorPagina);
            if (vm.ultimoRegistroDaPagina > vm.quantidadeDeRegistros) {
                vm.ultimoRegistroDaPagina = vm.quantidadeDeRegistros;
            }
        });
    };

    vm.excluir=function(pro){
        mensagemConfirmacaoExclusao(function (result) {
            if(result) {
              produto.excluir(pro.idProduto).then(function (retorno){
                  mensagemSucesso("Registro excluído com sucesso.");
                  vm.carregarProdutos();
              });
            }
        });
    };

    vm.salvarProduto = function(){
        produto.salvar(vm.produto).then(function (retorno) {
            mensagemSucesso("Registro salvo com Sucesso.");
            vm.limparCamposModalProduto();
            vm.carregarProdutos();
        });
    };

    vm.alterarProduto = function(pro){
        vm.produto = pro;
    };

    vm.limparCamposModalProduto = function () {
        vm.produto={};
    };

    vm.carregarProdutos();

});