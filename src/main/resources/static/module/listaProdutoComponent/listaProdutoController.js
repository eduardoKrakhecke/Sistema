
app.controller("listaProdutoController", function ($scope, $http, $location, $q,usuario, produto) {
    var vm = this;
    vm.produto={};
    vm.produtos=[];
    vm.tituloPanel="Produtos";

    var paginationOptions = {pageNumber: 1, pageSize: 10, sort: null};

        produto.getProdutoPaginado(paginationOptions.pageNumber, paginationOptions.pageSize).then(function (retorno) {
            vm.produtos = retorno.content;
        });


});