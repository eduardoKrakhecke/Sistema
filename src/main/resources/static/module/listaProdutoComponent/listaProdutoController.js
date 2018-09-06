
app.controller("listaProdutoController", function ($scope, $http, $location, $q,usuario, produto,uiGridConstants) {
    var vm = this;
    vm.produto={};
    vm.gridOptions = {};
    vm.tituloPanel="Produtos";

    var paginationOptions = {pageNumber: 1, pageSize: 10, sort: null};

        produto.getProdutoPaginado(paginationOptions.pageNumber, paginationOptions.pageSize).then(function (retorno) {
            vm.gridOptions.data = retorno.content;
            vm.gridOptions.totalItems = retorno.totalElements;
        });

    vm.gridOptions ={
        enableGridMenu: true,
        enableFiltering: true,
        useExternalFiltering: true,
        paginationPageSizes: [ 10, 20, 40, 60, 80, 100],
        paginationPageSize: paginationOptions.pageSize,
        enableColumnMenus:false,
        useExternalPagination: true,
        columnDefs: [
            { name: 'idProduto', displayName : 'Id', enableFiltering: false  },
            { name: 'descricao', displayName : 'Descrição' },
            { name: 'observacao',displayName : 'Observação' }
        ],
        onRegisterApi: function(gridApi) {
            vm.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged($scope,function (newPage, pageSize) {
                paginationOptions.pageNumber = newPage;
                paginationOptions.pageSize = pageSize;
                produto.getProdutoPaginado(paginationOptions.pageNumber, paginationOptions.pageSize).then(function (retorno) {
                    vm.gridOptions.data = retorno.content;
                    vm.gridOptions.totalItems = retorno.totalElements;
                });
            });


        }
    }

});