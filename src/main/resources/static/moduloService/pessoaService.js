
app.service('cliente', function($http) {
    this.getPessoasPaginadas = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/pessoasPaginadas?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

});
