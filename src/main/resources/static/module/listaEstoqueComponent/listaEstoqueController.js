app.controller("listaEstoqueController", function ( $scope, imprimir, estoque, unidadeMedida) {
    var vm = this;
    vm.estoque = {};
    vm.estoques = [];
    vm.unidadesMedidas =[];
    vm.quantidaDeRegistrosPorPagina="5";
    vm.pagina = 0;
    vm.filtro = "";
    vm.quantidadeDeRegistros = 0;
    vm.ultimaPagina = false;
    vm.primeiraPagina = false;
    vm.ultimoRegistroDaPagina = 0;
    vm.parametrosImpressao = {};

    vm.proximaPagina = function() {
        vm.pagina++;
        vm.carregarEstoques();
    };

    vm.paginaAnterior = function() {
        vm.pagina--;
        vm.carregarEstoques();
    };

    vm.filtrar = function(){
        vm.carregarEstoques();
    };

    vm.carregarEstoques=function(zerarPaginacao){
        if (zerarPaginacao){
            vm.pagina = 0;
        }
        estoque.getEstoquePaginado(vm.pagina, parseInt(vm.quantidaDeRegistrosPorPagina), vm.filtro ).then(function (retorno) {
            vm.estoques = retorno.conteudo;
            vm.quantidadeDeRegistros = retorno.quantidadeDeRegistros;
            vm.ultimaPagina = retorno.ultimaPagina;
            vm.primeiraPagina = retorno.primeiraPagina;
            vm.ultimoRegistroDaPagina = vm.pagina * vm.quantidadeDeRegistros + parseInt(vm.quantidaDeRegistrosPorPagina);
            if (vm.ultimoRegistroDaPagina > vm.quantidadeDeRegistros) {
                vm.ultimoRegistroDaPagina = vm.quantidadeDeRegistros;
            }
        });
    };

    unidadeMedida.getUnidadeMedida().then(function (retorno) {
        vm.unidadesMedidas = retorno;
    });

    vm.carregaAutoComplete = function () {
        var dataList;
        var parametro = vm.estoque.produto;
        if(parametro.trim().length > 2){
            estoque.getProdutoAutoComplete(parametro).then(function (retorno) {
                dataList = angular.element(document.querySelector('#produtos'));
                dataList.empty();
                if(retorno.length) {
                    for(var i=0, len=retorno.length; i<len; i++) {
                        var opt = $("<option >"+ retorno[i].descricao +" </option>").attr("value", retorno[i].idProduto);
                        //var tempObj = [retorno[i]['descricao']] = retorno[i]['idProduto'];
                        dataList.append(opt);
                    }
                }
            });
        }
    };


    vm.salvarEstoque = function() {
        estoque.salvar(vm.estoque).then(function (retorno) {
            mensagemSucesso("Registro salvo com sucesso");
            vm.limparDadosModal();
            vm.carregarEstoques();
        });
    };

    vm.carregarEstoques();

});