
app.service('produto', function($http) {
    this.getProdutoPaginado = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/produtosPaginados?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    }
});
