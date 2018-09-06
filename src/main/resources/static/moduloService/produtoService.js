
app.service('produto', function($http) {
    this.getProdutoPaginado = function(pageNumber,size) {
        pageNumber = pageNumber > 0?pageNumber - 1:0;
        return $http({method : 'GET', async:false, url : '/user/produtosPaginados?page='+pageNumber+'&size='+size}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    }
});
