
app.controller("listaClienteController", function ( $scope, $http, $q,usuario, endereco, cliente, imprimir) {
    var vm = this;
    vm.doc = {};
    vm.pessoa={};
    vm.pessoa.pessoaDocumentos = [];
    vm.pessoas=[];
    vm.tituloPanel="Clientes";
    vm.quantidaDeRegistrosPorPagina=12;
    vm.pagina = 0;
    vm.filtro = "";
    vm.quantidadeDeRegistros = 0;
    vm.ultimaPagina = false;
    vm.primeiraPagina = false;
    vm.ultimoRegistroDaPagina = 0;
    vm.parametrosImpressao = {};
    vm.habilitaFiltro = true;

    vm.filtrar = function(){
        if(vm.filtro.trim().length > 2){
            vm.carregarClientes();
        }
        if(vm.filtro === ""){
            vm.carregarClientes();
        }
    };


    vm.carregarClientes=function( ){
     vm.pagina =0;
        cliente.getPessoasPaginadas(vm.pagina, vm.quantidaDeRegistrosPorPagina, vm.filtro ).then(function (retorno) {
            vm.pessoas = retorno.conteudo;
            vm.quantidadeDeRegistros = retorno.quantidadeDeRegistros;
        });
    };

    vm.carregarClientesRolagem = function(event,filtro){
        if(filtro!==undefined){
            vm.pagina =0;
        } else{
            filtro ="";
            vm.pagina ++;
        }
        cliente.getPessoasPaginadas(vm.pagina, vm.quantidaDeRegistrosPorPagina, filtro ).then(function (retorno) {
            for(var i =0; i <retorno.conteudo.length; i++){
                vm.pessoas.push(retorno.conteudo[i]);
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
          vm.filtro="";
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
        if(pesDoc.idPessoaDocumento === undefined){
            pos = vm.pessoa.pessoaDocumentos.indexOf(pesDoc);
            vm.pessoa.pessoaDocumentos.splice(pos, 1);
        } else{
            cliente.excluirPessoaDocumento(pesDoc.idPessoaDocumento).then(function (retorno){
                pos = vm.pessoa.pessoaDocumentos.indexOf(pesDoc);
                vm.pessoa.pessoaDocumentos.splice(pos, 1);
            });
        }
    };

    vm.excluir = function(pes){
        mensagemConfirmacaoExclusao(pes.nome,function (result) {
            if(result) {
                cliente.excluir(pes.idPessoa).then(function (retorno){
                    mensagemSucesso("Registro excluído com sucesso.");
                    vm.filtro="";
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


});