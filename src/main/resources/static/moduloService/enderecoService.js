app.service('endereco', function($http) {
    this.getPaises = function() {
        return $http({method : 'GET', async:false, url : '/user/paises'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.getUfs = function() {
        return $http({method : 'GET', async:false, url : '/user/ufs'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.getMunicipios = function(idUf) {
        return $http({method : 'GET', async:false, url : '/user/municipios?idUf='+idUf}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

});
