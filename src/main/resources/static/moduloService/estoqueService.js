
app.service('estoque', function($http) {

    this.getEstoquePaginado = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/estoquePaginado?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };


});
