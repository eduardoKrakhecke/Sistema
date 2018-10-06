
app.service('estoque', function($http) {

    this.getEstoquePaginado = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/estoquePaginado?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.getProdutoAutoComplete = function(parametro) {
        return $http({method : 'GET', async:false, url : '/user/produtosAutoComplete?parametro='+parametro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.salvar = function(estoque) {
        return $http({method : 'POST', url : '/user/estoque', data: estoque}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

});
