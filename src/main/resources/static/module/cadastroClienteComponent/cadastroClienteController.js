
app.controller("cadastroClienteController", function ($scope, usuario, endereco, cliente) {
    var vm = this;
    $scope.paises=[];
    $scope.ufs=[];
    $scope.municipios=[];

    vm.pessoa={};

    vm.tituloPanel="Clientes";

    endereco.getPaises().then(function(retorno){
        $scope.paises = retorno;
    });

    endereco.getUfs().then(function(retorno){
        $scope.ufs = retorno;

    });

    vm.selecionaMunicipioById = function(idUf){
        endereco.getMunicipios(idUf).then(function(retorno){
            $scope.municipios = retorno;
        });
    }


});