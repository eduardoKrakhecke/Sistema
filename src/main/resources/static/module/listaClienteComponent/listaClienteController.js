
app.controller("listaClienteController", function ( $scope, $http, $q,usuario, endereco, cliente, imprimir) {
    var vm = this;
    vm.doc = {};
    vm.pessoa={};
    vm.pessoa.pessoaDocumentos = [];
    vm.pessoas=[];
    vm.tituloPanel="Clientes";
    vm.quantidaDeRegistrosPorPagina="5";
    vm.pagina = 0;
    vm.filtro = "";
    vm.quantidadeDeRegistros = 0;
    vm.ultimaPagina = false;
    vm.primeiraPagina = false;
    vm.ultimoRegistroDaPagina = 0;
    vm.parametrosImpressao = {};
    vm.habilitaFiltro = true;
    vm.capturarFoto = false;



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
            if( vm.pessoa.uf !== undefined){
                vm.pessoa.uf.idUf = null;
                vm.pessoa.uf.idMunicipio = null;
            }
        }
    };

    vm.carregaImagem = function(){
        carregaImagem();
    };

    vm.adicionarDocumento = function () {
        vm.pessoa.pessoaDocumentos.push(vm.doc);
        vm.doc={};
    };

   vm.salvarCliente = function() {
       if(document.getElementById("imagem").getAttribute("src")!=='./image/semfoto.png'){
           vm.pessoa.foto = document.getElementById("imagem").getAttribute("src");
       }
       else{
           vm.pessoa.foto = null;
       }
       angular.merge(vm.pessoa,vm.pessoa.foto);
      cliente.salvar(vm.pessoa).then(function (retorno) {
          mensagemSucesso("Registro salvo com sucesso");
          vm.limparDadosModal();
          vm.carregarClientes();
      });
    };

   vm.limparDadosModal = function (){
       vm.pessoa = {};
       vm.capturarFoto = false;
   };

    vm.limparModalDocumentos = function() {
        vm.doc = {};
    };

    vm.alterar = function(pes){
        vm.pessoa = pes;
        vm.selecionaId(pes.uf.idUf);
    };

    vm.removerItemLista = function(pesDoc){
        cliente.excluirPessoaDocumento(pesDoc.idPessoaDocumento).then(function (retorno){
            pos = vm.pessoa.pessoaDocumentos.indexOf(pesDoc);
            vm.pessoa.pessoaDocumentos.splice(pos, 1);
        });
    };

    vm.excluir = function(pes){
        mensagemConfirmacaoExclusao(function (result) {
            if(result) {
                cliente.excluir(pes.idPessoa).then(function (retorno){
                    mensagemSucesso("Registro excluído com sucesso.");
                    vm.carregarClientes();
                });
            }
        });
    };


    vm.gerarDocumento = function() {
        carregando();
        imprimir.imprimir({parametrosImpressao: vm.parametrosImpressao, semParametros: vm.habilitaFiltro}).then(function (retorno) {
            var corpoPdf = new Blob([retorno], {type: 'application/pdf'});
            var caminhoURL = URL.createObjectURL(corpoPdf);
            window.open(caminhoURL);
            fecharModalLoad();
            vm.limparModalImprimirCliente();
        });
    };


    vm.limparModalImprimirCliente = function () {
        vm.parametrosImpressao = {};
        vm.habilitaFiltro = true;
    };

    vm.carregarClientes();




});