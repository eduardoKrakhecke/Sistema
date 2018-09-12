
app.controller("cadastroProdutoController", function ( $http, $location, $q,usuario, produto) {
    var vm = this;
    vm.produto={};

    vm.salvarProduto = function(){
        produto.salvar(vm.produto).then(function (retorno) {

        });
        vm.carregarProdutos();
    };

});