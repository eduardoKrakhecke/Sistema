
app.service('clienteService', function($http) {
    this.getCliente = function() {
        return $http({method : 'GET', async:false, url : '/user/clientes'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.salvar = function(cliente) {
        return $http({method : 'POST', url : '/user/cliente', data: cliente}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.excluir = function(idCliente) {
        return $http({method : 'DELETE', url : '/user/cliente/'+ idCliente}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };


});
