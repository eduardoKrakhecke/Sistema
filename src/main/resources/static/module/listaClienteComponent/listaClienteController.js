
app.controller("listaClienteController", function ($scope, $http, $q,usuario, endereco, cliente) {
    var vm = this;
    vm.doc = {};
    vm.pessoasDocumentos = [];
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

    endereco.getPaises().then(function(retorno){
        $scope.paises = retorno;
    });

    endereco.getUfs().then(function(retorno){
        $scope.ufs = retorno;

    });

    vm.selecionaId = function(idUf){
        endereco.getMunicipios(idUf).then(function(retorno){
            $scope.municipios = retorno;
        });
    };

    vm.selecionaPais = function(id){
        if(id!==31){
            vm.pessoa.idUf = null;
            vm.pessoa.idMunicipio = null;
        }
    };

    vm.carregaImagem = function(){
        carregaImagem();
    };

    vm.adicionarDocumento = function () {
        vm.pessoasDocumentos.push(vm.doc)
        vm.doc={};
    };

    vm.carregarClientes();

});