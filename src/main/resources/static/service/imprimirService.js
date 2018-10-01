app.service('imprimir', function($http) {

    this.imprimir = function(parametrosImpressao) {
        return $http({method : 'POST', url : '/user/imprimirCliente', data: parametrosImpressao, responseType: 'arraybuffer'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

});