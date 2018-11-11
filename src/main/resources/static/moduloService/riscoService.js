
app.service('risco', function($http) {

    this.getRisco = function() {
        return $http({method : 'GET', async:false, url : '/user/risco'}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };


});
