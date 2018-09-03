
app.service('usuario', function($http) {
    this.getUsuario = function() {
        return $http({method : 'GET', async:false, url : '/user/usuario'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    }
});
