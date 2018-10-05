
app.service('unidadeMedida', function($http) {

    this.getUnidadeMedida = function() {
        return $http({method : 'GET', async:false, url : '/user/unidadesMedidas'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };


});
