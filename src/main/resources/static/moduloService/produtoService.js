
app.service('produto', function($http) {
    this.getProdutoPaginado = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/produtosPaginados?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.salvar = function(produto) {
        return $http({method : 'POST', url : '/user/produtos', data: produto}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.excluir = function(idProduto) {
        return $http({method : 'DELETE', url : '/user/produto/'+ idProduto}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    }
});
