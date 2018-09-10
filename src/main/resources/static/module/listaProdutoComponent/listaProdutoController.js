
app.controller("listaProdutoController", function ($scope, $http, $location, $q,usuario, produto) {
    var vm = this;
    vm.produto={};
    vm.produtos=[];
    vm.tituloPanel="Produtos";

    vm.pageNumber=0;
    vm.pageSize=10;
    vm.filtro="";

    vm.filtrar = function(){
        vm.carregarProdutos();
    };

    vm.carregarProdutos=function(){
        produto.getProdutoPaginado(vm.pageNumber, vm.pageSize, vm.filtro ).then(function (retorno) {
            vm.produtos = retorno.conteudo;
        });
    };


});