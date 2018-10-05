app.controller("listaEstoqueController", function (imprimir, estoque, unidadeMedida) {
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

    vm.carregaAutoComplete = function(parametro) {
        if (parametro.trim() > 3) {
            estoque.getProdutoAutoComplete(parametro).then(function (retorno) {
                vm.produtos = retorno;
            });
        }
    };

    vm.carregarEstoques();

});