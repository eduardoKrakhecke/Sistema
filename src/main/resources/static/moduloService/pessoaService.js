
app.service('cliente', function($http) {
    this.getPessoasPaginadas = function(pageNumber,size,filtro) {
        return $http({method : 'GET', async:false, url : '/user/pessoasPaginadas?page='+pageNumber+'&size='+size +'&filtro='+filtro}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.salvar = function(pessoa) {
        return $http({method : 'POST', url : '/user/pessoas', data: pessoa}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    };

    this.excluir = function(idPessoa) {
        return $http({method : 'DELETE', url : '/user/pessoas/'+ idPessoa}).then(function(response) {
            var retorno=response.data;
            return retorno;
        });
    }

});
